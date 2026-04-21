package com.app.simostools.core.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.BluetoothGatt.CONNECTION_PRIORITY_HIGH
import android.bluetooth.le.*
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.ParcelUuid
import com.app.simostools.core.io.ConfigSettings
import com.app.simostools.core.uds.*
import com.app.simostools.core.utils.DebugLog
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Semaphore

interface BleManagerCallbacks {
    fun onConnectionStateChange(state: BLEConnectionState)
    fun onScanResult(name: String, address: String)
    fun onTaskStateChanged(task: UDSTask)
    fun onPacketReceived(packet: ByteArray)
    fun onLoggingStatusChanged(enabled: Boolean)
    fun onFlashInfo(info: String)
    fun onFlashProgress(progress: Int)
    fun onFlashConfirmRequired()
    fun onFlashButtonReset()
    fun onUtilityInfo(info: String)
}

@SuppressLint("MissingPermission")
class BleManager(private val context: Context, private val callbacks: BleManagerCallbacks) {
    private val TAG = "BleManager"

    private var mScanning: Boolean = false
    var mConnectionState: BLEConnectionState = BLEConnectionState.NONE
        private set
    private val mWriteSemaphore: Semaphore = Semaphore(1)
    private val mReadQueue: ConcurrentLinkedQueue<ByteArray> = ConcurrentLinkedQueue<ByteArray>()
    private val mWriteQueue: ConcurrentLinkedQueue<ByteArray> = ConcurrentLinkedQueue<ByteArray>()
    private var mBluetoothGatt: BluetoothGatt? = null
    private var mBluetoothDevice: BluetoothDevice? = null
    private var mConnectionThread: ConnectionThread? = null
    private var mLogWriteState: Boolean = false
    private var mScanningTimer: Timer? = null
    private var mMTUSize: Int = 23
    private var mFinished: Boolean = false

    private val mBluetoothManager =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val mBluetoothAdapter = mBluetoothManager.adapter

    private fun BluetoothGattCharacteristic.isReadable(): Boolean =
        containsProperty(BluetoothGattCharacteristic.PROPERTY_READ)

    private fun BluetoothGattCharacteristic.isWritable(): Boolean =
        containsProperty(BluetoothGattCharacteristic.PROPERTY_WRITE)

    private fun BluetoothGattCharacteristic.isWritableWithoutResponse(): Boolean =
        containsProperty(BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)

    private fun BluetoothGattCharacteristic.isIndicatable(): Boolean =
        containsProperty(BluetoothGattCharacteristic.PROPERTY_INDICATE)

    private fun BluetoothGattCharacteristic.isNotifiable(): Boolean =
        containsProperty(BluetoothGattCharacteristic.PROPERTY_NOTIFY)

    private fun BluetoothGattCharacteristic.containsProperty(property: Int): Boolean =
        properties and property != 0

    private val mScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            result.device?.let { device ->
                val name = device.name ?: "Unknown"
                callbacks.onScanResult(name, device.address)
            }
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            DebugLog.e(TAG, "onScanFailed: code $errorCode", Exception("BLE Scan Failed"))
        }
    }

    private val mGattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            val deviceName = gatt.device.name

            if (mBluetoothDevice != gatt.device) {
                gatt.safeClose()
                return
            }

            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    try {
                        mBluetoothGatt = gatt
                        Handler(Looper.getMainLooper()).post {
                            gatt.discoverServices()
                        }
                    } catch (e: Exception) {
                        disconnect()
                    }
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    gatt.getService(BLE_SERVICE_UUID)?.getCharacteristic(BLE_DATA_RX_UUID)?.let {
                        disableNotifications(it)
                    }
                    if (gatt != mBluetoothGatt) {
                        gatt.safeClose()
                    }
                    disconnect()
                }
            } else {
                if (gatt != mBluetoothGatt) {
                    gatt.safeClose()
                }
                val bleState = BLEConnectionState.ERROR
                bleState.errorMessage = status.toString()
                disconnect(bleState)
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            super.onServicesDiscovered(gatt, status)
            if (gatt != mBluetoothGatt) {
                gatt.safeClose()
                return
            }

            if (status == BluetoothGatt.GATT_SUCCESS) {
                try {
                    gatt.requestMtu(BLE_GATT_MTU_SIZE)
                } catch (e: Exception) {
                    disconnect()
                }
            } else {
                val bleState = BLEConnectionState.ERROR
                bleState.errorMessage = status.toString()
                disconnect(bleState)
            }
        }

        override fun onMtuChanged(gatt: BluetoothGatt, mtu: Int, status: Int) {
            super.onMtuChanged(gatt, mtu, status)
            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (gatt != mBluetoothGatt) {
                    gatt.safeClose()
                    return
                }
                mMTUSize = mtu
                setConnectionState(BLEConnectionState.CONNECTED)
                try {
                    gatt.requestConnectionPriority(CONNECTION_PRIORITY_HIGH)
                    enableNotifications(
                        gatt.getService(BLE_SERVICE_UUID)!!.getCharacteristic(BLE_DATA_RX_UUID)
                    )
                } catch (e: Exception) {
                    disconnect()
                }
            } else {
                if (gatt != mBluetoothGatt) {
                    gatt.safeClose()
                }
                val newState = BLEConnectionState.ERROR
                newState.errorMessage = status.toString()
                disconnect(newState)
            }
        }

        override fun onDescriptorWrite(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            super.onDescriptorWrite(gatt, descriptor, status)
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            mWriteSemaphore.release()
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            with(characteristic) {
                var data = value
                val bleHeader = BLEHeader()
                while (data.isNotEmpty()) {
                    bleHeader.fromByteArray(data)
                    if (bleHeader.cmdSize + 8 <= data.size) {
                        mReadQueue.add(data.copyOfRange(0, bleHeader.cmdSize + 8))
                        data = data.copyOfRange(bleHeader.cmdSize + 8, data.size)
                    } else {
                        data = byteArrayOf()
                    }
                }
            }
        }
    }

    fun startScan() {
        stopScanning()
        mScanningTimer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                stopScanning()
            }
        }
        mScanningTimer?.schedule(task, BLE_SCAN_PERIOD)

        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()

        val scanFilter = listOf(
            ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(BLE_SERVICE_UUID.toString()))
                .build()
        )

        try {
            mBluetoothAdapter?.bluetoothLeScanner?.startScan(scanFilter, settings, mScanCallback)
            mScanning = true
        } catch (e: Exception) {
            DebugLog.e(TAG, "Exception starting scan", e)
        }
    }

    fun stopScanning() {
        if (mScanning) {
            mScanningTimer?.cancel()
            mScanningTimer?.purge()
            mScanningTimer = null
            mBluetoothAdapter?.bluetoothLeScanner?.stopScan(mScanCallback)
            mScanning = false
        }
    }

    fun connect(address: String) {
        val device = mBluetoothAdapter?.getRemoteDevice(address)
        connect(device)
    }

    fun connect(device: BluetoothDevice?) {
        disconnect()
        if (device != null) {
            mBluetoothDevice = device
            setConnectionState(BLEConnectionState.CONNECTING)
            device.connectGatt(context, false, mGattCallback, 2)
            return
        }
        setConnectionState(BLEConnectionState.NONE)
    }

    fun disconnect(newState: BLEConnectionState = BLEConnectionState.NONE) {
        stopScanning()
        closeConnectionThread()
        mBluetoothDevice = null
        mBluetoothGatt?.let {
            it.safeClose()
            mBluetoothGatt = null
        }
        if (!mFinished) {
            setConnectionState(newState)
        }
    }

    fun setTask(task: UDSTask) {
        mConnectionThread?.setTaskState(task)
    }

    fun stopTask() {
        mConnectionThread?.setTaskState(UDSTask.NONE)
    }

    fun destroy() {
        mFinished = true
        disconnect()
    }

    private fun BluetoothGatt.safeClose() {
        try {
            this.close()
        } catch (e: Exception) {
            DebugLog.e(TAG, "Exception while closing connection", e)
        }
    }

    private fun writeDescriptor(descriptor: BluetoothGattDescriptor, payload: ByteArray) {
        mBluetoothGatt?.let { gatt ->
            descriptor.value = payload
            gatt.writeDescriptor(descriptor)
        } ?: error("Not connected to a BLE device!")
    }

    private fun enableNotifications(characteristic: BluetoothGattCharacteristic) {
        val payload = when {
            characteristic.isIndicatable() -> BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
            characteristic.isNotifiable() -> BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            else -> return
        }

        characteristic.getDescriptor(BLE_CCCD_UUID)?.let { cccDescriptor ->
            if (mBluetoothGatt?.setCharacteristicNotification(characteristic, true) == true) {
                writeDescriptor(cccDescriptor, payload)
            }
        }
    }

    private fun disableNotifications(characteristic: BluetoothGattCharacteristic) {
        if (!characteristic.isNotifiable() && !characteristic.isIndicatable()) return

        characteristic.getDescriptor(BLE_CCCD_UUID)?.let { cccDescriptor ->
            if (mBluetoothGatt?.setCharacteristicNotification(characteristic, false) == true) {
                writeDescriptor(cccDescriptor, BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)
            }
        }
    }

    private fun closeConnectionThread() {
        mConnectionThread?.cancel()
        mConnectionThread = null
    }

    private fun createConnectionThread() {
        closeConnectionThread()
        mConnectionThread = ConnectionThread()
        mConnectionThread?.let { thread ->
            thread.priority = BLE_THREAD_PRIORITY
            thread.start()
        }
    }

    private fun setConnectionState(newState: BLEConnectionState) {
        if (mConnectionState == newState) return
        when (newState) {
            BLEConnectionState.ERROR, BLEConnectionState.NONE -> closeConnectionThread()
            BLEConnectionState.CONNECTING -> {}
            BLEConnectionState.CONNECTED -> createConnectionThread()
        }
        mConnectionState = newState
        mConnectionState.deviceName = mBluetoothGatt?.device?.name ?: ""
        callbacks.onConnectionStateChange(mConnectionState)
    }

    private inner class ConnectionThread : Thread() {
        private var mTask: UDSTask = UDSTask.NONE
        private var mTaskNext: UDSTask = UDSTask.NONE
        private var mTaskTick: Int = 0
        private var mTaskTime: Long = 0
        private var mTaskTimeNext: Long = 0
        private var mTaskTimeOut: Long = 0
        private var mTaskNextBroadcast: Long = 0
        private var mPasswordAccepted: Boolean = true

        init {
            setTaskState(UDSTask.NONE)
        }

        override fun run() {
            while (mConnectionState == BLEConnectionState.CONNECTED && !currentThread().isInterrupted) {
                if (!mWriteQueue.isEmpty() && mWriteSemaphore.tryAcquire()) {
                    try {
                        val buff = mWriteQueue.poll()
                        buff?.let {
                            DebugLog.c(TAG, buff, true)
                            mBluetoothGatt?.let { gatt ->
                                val txChar = gatt.getService(BLE_SERVICE_UUID)!!
                                    .getCharacteristic(BLE_DATA_TX_UUID)
                                val writeType = when {
                                    txChar.isWritable() -> BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
                                    txChar.isWritableWithoutResponse() -> BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
                                    else -> error("Characteristic ${txChar.uuid} cannot be written to")
                                }
                                txChar.writeType = writeType
                                txChar.value = it
                                gatt.writeCharacteristic(txChar)
                            } ?: error("Not connected to a BLE device!")
                        }
                    } catch (e: Exception) {
                        mWriteSemaphore.release()
                        cancel()
                        break
                    }
                }

                if (!mReadQueue.isEmpty()) {
                    try {
                        val buff = mReadQueue.poll()
                        buff?.let {
                            DebugLog.c(TAG, buff, false)
                            processPacket(buff)
                        }
                    } catch (e: Exception) {
                        cancel()
                        break
                    }
                }

                if (mTaskNext != UDSTask.NONE) {
                    if (mTaskTimeNext < System.currentTimeMillis() || mTaskTimeOut < System.currentTimeMillis()) {
                        startNextTask()
                    }
                } else if (mTaskTimeNext < System.currentTimeMillis()) {
                    processPacket(null)
                }
            }
        }

        fun cancel() {
            interrupt()
        }

        @Synchronized
        fun setTaskState(newTask: UDSTask) {
            if (mConnectionState != BLEConnectionState.CONNECTED) {
                mTask = UDSTask.NONE
                return
            }
            if (newTask == mTask) return
            mTaskTimeNext = System.currentTimeMillis() + TASK_END_DELAY
            mTaskTimeOut = System.currentTimeMillis() + TASK_END_TIMEOUT
            mTaskNext = newTask
            if (mTask != UDSTask.NONE) stopTask()
        }

        private fun writePacket(buff: ByteArray?) {
            buff?.let {
                try {
                    var buffer = it
                    if (buffer.size < 8) return
                    var packetSize = mMTUSize - 3
                    if (buffer.size > packetSize) {
                        it[1] =
                            ((it[1].toInt() or BLECommandFlags.SPLIT_PK.value) and 0xFF).toByte()
                        mWriteQueue.add(buffer.copyOfRange(0, packetSize))
                        buffer = buffer.copyOfRange(packetSize, buffer.size)
                        packetSize -= BLEHeader().size_partial()
                        var packetCount = 1
                        while (buffer.isNotEmpty()) {
                            val dataSize = if (buffer.size > packetSize) packetSize else buffer.size
                            mWriteQueue.add(
                                byteArrayOf(
                                    BLE_HEADER_PT.toByte(),
                                    (packetCount++ and 0xFF).toByte()
                                ) + buffer.copyOfRange(0, dataSize)
                            )
                            buffer = buffer.copyOfRange(dataSize, buffer.size)
                        }
                    } else {
                        mWriteQueue.add(buffer)
                    }
                } catch (e: Exception) {
                    DebugLog.e(TAG, "Exception while writing packet.", e)
                }
            }
        }

        private fun startNextTask() {
            mTaskTimeNext = System.currentTimeMillis() + TASK_BUMP_DELAY
            mTask = mTaskNext
            mTaskNext = UDSTask.NONE
            mTaskTick = 0
            mTaskTime = System.currentTimeMillis()
            callbacks.onTaskStateChanged(mTask)
            when (mTask) {
                UDSTask.LOGGING -> startTaskLogging()
                UDSTask.FLASHING -> startTaskFlashing()
                UDSTask.TUNE_INFO -> startTaskGetTuneInfo()
                UDSTask.INFO -> startTaskGetInfo()
                UDSTask.DTC_GET -> startTaskGetDTC()
                UDSTask.DTC_CLEAR -> startTaskClearDTC()
                UDSTask.SET_ADAPTER -> startTaskSetAdapter()
                UDSTask.NONE -> {}
            }
        }

        private fun stopTask() {
            mTask = UDSTask.NONE
            callbacks.onTaskStateChanged(mTask)
            setBridgeLED(0, 0x80, 0)
            clearBridgePersist()
        }

        private fun startTaskLogging() {
            try {
                setBridgePersistDelay(1000 / ConfigSettings.LOGGING_RATE.toInt())
                setBridgePersistQDelay(ConfigSettings.Q_CORRECTION.toInt())
            } catch (e: Exception) {
            }
            UDSLogger.setModeDSG(ConfigSettings.LOG_DSG.toBoolean())
            writePacket(UDSLogger.startTask(0))
        }

        private fun startTaskFlashing() {
            setBridgeSTMIN(350)
            writePacket(UDSFlasher.startTask(0))
        }

        private fun startTaskGetInfo() {
            writePacket(UDSInfo.startTask(0))
        }

        private fun startTaskGetTuneInfo() {
            writePacket(UDSInfo.startTask(TUNE_INFO_PIDS[0]))
        }

        private fun startTaskClearDTC() {
            writePacket(UDSdtc.startTask(0, true))
        }

        private fun startTaskGetDTC() {
            writePacket(UDSdtc.startTask(0, false))
        }

        private fun startTaskSetAdapter() {
            changeGAPName(ConfigSettings.ADAPTER_NAME.value.toString())
            setTaskState(UDSTask.NONE)
        }

        private fun processPacket(buff: ByteArray?) {
            if (mPasswordAccepted) {
                when (mTask) {
                    UDSTask.NONE -> processPacketNone(buff)
                    UDSTask.LOGGING -> processPacketLogging(buff)
                    UDSTask.FLASHING -> processPacketFlashing(buff)
                    UDSTask.TUNE_INFO -> processPacketTuneInfo(buff)
                    UDSTask.INFO -> processPacketGetInfo(buff)
                    UDSTask.DTC_GET -> processPacketGetDTC(buff)
                    UDSTask.DTC_CLEAR -> processPacketClearDTC(buff)
                    UDSTask.SET_ADAPTER -> processPacketSetAdapter(buff)
                }
                buff?.let { if (it.size >= 8) mTaskTick++ }
                mTaskTimeNext =
                    System.currentTimeMillis() + (if (mTaskNext != UDSTask.NONE) TASK_END_DELAY else TASK_BUMP_DELAY).toLong()
            } else {
                buff?.let {
                    if (it.size == 9) {
                        val bleHeader = BLEHeader()
                        bleHeader.fromByteArray(it)
                        if (bleHeader.isValid() && it[8] == 0xFF.toByte()) {
                            mPasswordAccepted = true
                        } else {
                            disconnect()
                        }
                    }
                }
            }
        }

        private fun processPacketNone(buff: ByteArray?) {
            buff?.let {
                if (buff.size > 8) {
                    callbacks.onPacketReceived(buff.copyOfRange(8, buff.size))
                }
            }
        }

        private fun processPacketLogging(buff: ByteArray?) {
            buff?.let {
                val result = UDSLogger.processPacket(mTaskTick, buff, context)
                if (mTaskTick < UDSLogger.frameCount() - 1) {
                    if (result != UDSReturn.OK) setTaskState(UDSTask.NONE)
                    else writePacket(UDSLogger.startTask(mTaskTick + 1))
                } else {
                    if (result != UDSReturn.OK) setTaskState(UDSTask.NONE)
                    else {
                        if (System.currentTimeMillis() > mTaskNextBroadcast) {
                            // callbacks.onLoggingUpdate(...) // if needed
                            mTaskNextBroadcast =
                                System.currentTimeMillis() + (1000 / (ConfigSettings.DISPLAY_RATE.toInt())).toLong()
                        }
                        if (UDSLogger.isEnabled() != mLogWriteState) {
                            callbacks.onLoggingStatusChanged(UDSLogger.isEnabled())
                            if (UDSLogger.isEnabled()) setBridgeLED(0, 0, 0x80)
                            else setBridgeLED(0, 0x80, 0)
                            mLogWriteState = UDSLogger.isEnabled()
                        }
                    }
                }
            } ?: run {
                if (UDSLogger.processPacket(mTaskTick, null, context) != UDSReturn.OK) setTaskState(
                    UDSTask.NONE
                )
            }
        }

        private fun processPacketFlashing(buff: ByteArray?) {
            if (buff != null) {
                val response = buff.copyOfRange(8, buff.size)
                val flashStatus = UDSFlasher.processFlashCAL(mTaskTick, response)
                if (UDSFlasher.getInfo() != "") callbacks.onFlashInfo(UDSFlasher.getInfo())
                val progress = UDSFlasher.getProgress()
                callbacks.onFlashProgress(progress)

                when (flashStatus) {
                    UDSReturn.FLASH_CONFIRM -> callbacks.onFlashConfirmRequired()
                    UDSReturn.OK -> callbacks.onFlashButtonReset()
                    UDSReturn.ABORTED -> callbacks.onFlashButtonReset()
                    UDSReturn.FLASH_COMPLETE -> {}
                    UDSReturn.CLEAR_DTC_REQUEST -> {
                        val bleHeader = BLEHeader()
                        bleHeader.rxID = 0x7E8
                        bleHeader.txID = 0x700
                        bleHeader.cmdSize = 1
                        bleHeader.cmdFlags = BLECommandFlags.PER_CLEAR.value
                        mWriteQueue.add(bleHeader.toByteArray() + byteArrayOf(0x04.toByte()))
                    }

                    UDSReturn.COMMAND_QUEUED -> writePacket(buildBLEFrame(UDSFlasher.getCommand()))
                    else -> setTaskState(UDSTask.NONE)
                }
            } else {
                if (UDSFlasher.getSubtask() != FLASH_ECU_CAL_SUBTASK.FLASH_BLOCK && UDSFlasher.getSubtask() != FLASH_ECU_CAL_SUBTASK.PATCH_BLOCK) {
                    mWriteQueue.add(buildBLEFrame(UDS_COMMAND.TESTER_PRESENT.bytes))
                }
            }
        }

        private fun processPacketGetInfo(buff: ByteArray?) {
            buff?.let {
                if (UDSInfo.processPacket(mTaskTick, buff) == UDSReturn.OK) {
                    callbacks.onUtilityInfo(UDSInfo.getInfo())
                    if (mTaskTick < UDSInfo.getStartCount() - 1) writePacket(
                        UDSInfo.startTask(
                            mTaskTick + 1
                        )
                    )
                    else setTaskState(UDSTask.NONE)
                } else setTaskState(UDSTask.NONE)
            } ?: run {
                if (UDSInfo.processPacket(
                        mTaskTick,
                        null
                    ) != UDSReturn.OK
                ) setTaskState(UDSTask.NONE)
            }
        }

        private fun processPacketTuneInfo(buff: ByteArray?) {
            buff?.let {
                if (UDSInfo.processPacket(TUNE_INFO_PIDS[mTaskTick], buff) == UDSReturn.OK) {
                    callbacks.onFlashInfo(UDSInfo.getInfo())
                    if (mTaskTick < TUNE_INFO_PIDS.size - 1) writePacket(
                        UDSInfo.startTask(
                            TUNE_INFO_PIDS[mTaskTick + 1]
                        )
                    )
                    else setTaskState(UDSTask.NONE)
                } else setTaskState(UDSTask.NONE)
            } ?: run {
                if (UDSInfo.processPacket(
                        mTaskTick,
                        null
                    ) != UDSReturn.OK
                ) setTaskState(UDSTask.NONE)
            }
        }

        private fun processPacketGetDTC(buff: ByteArray?) {
            buff?.let {
                when (UDSdtc.processPacket(mTaskTick, buff, false)) {
                    UDSReturn.OK -> if (mTaskTick < UDSdtc.getStartCount(false) - 1) writePacket(
                        UDSdtc.startTask(mTaskTick + 1, false)
                    )

                    UDSReturn.COMPLETE -> {
                        callbacks.onUtilityInfo(UDSdtc.getInfo()); setTaskState(UDSTask.NONE)
                    }

                    else -> {
                        callbacks.onUtilityInfo(UDSdtc.getInfo()); setTaskState(UDSTask.NONE)
                    }
                }
            } ?: run {
                if (UDSdtc.processPacket(mTaskTick, null, false) != UDSReturn.OK) setTaskState(
                    UDSTask.NONE
                )
            }
        }

        private fun processPacketClearDTC(buff: ByteArray?) {
            buff?.let {
                if (UDSdtc.processPacket(mTaskTick, buff, true) == UDSReturn.OK) {
                    callbacks.onUtilityInfo(UDSdtc.getInfo())
                    if (mTaskTick < UDSdtc.getStartCount(true) - 1) writePacket(
                        UDSdtc.startTask(
                            mTaskTick + 1,
                            true
                        )
                    )
                    else setTaskState(UDSTask.NONE)
                } else setTaskState(UDSTask.NONE)
            } ?: run {
                if (UDSdtc.processPacket(mTaskTick, null, true) != UDSReturn.OK) setTaskState(
                    UDSTask.NONE
                )
            }
        }

        private fun processPacketSetAdapter(buff: ByteArray?) {}

        private fun clearBridgePersist() {
            val bleHeader = BLEHeader()
            bleHeader.cmdSize = 0
            bleHeader.cmdFlags = BLECommandFlags.PER_CLEAR.value
            writePacket(bleHeader.toByteArray())
        }

        private fun setBridgePersistDelay(delay: Int) {
            val bleHeader = BLEHeader()
            bleHeader.cmdSize = 2
            bleHeader.cmdFlags = BLECommandFlags.SETTINGS.value or BLESettings.PERSIST_DELAY.value
            mWriteQueue.add(
                bleHeader.toByteArray() + byteArrayOf(
                    (delay and 0xFF).toByte(),
                    ((delay and 0xFF00) shr 8).toByte()
                )
            )
        }

        private fun setBridgePersistQDelay(delay: Int) {
            val bleHeader = BLEHeader()
            bleHeader.cmdSize = 2
            bleHeader.cmdFlags = BLECommandFlags.SETTINGS.value or BLESettings.PERSIST_Q_DELAY.value
            mWriteQueue.add(
                bleHeader.toByteArray() + byteArrayOf(
                    (delay and 0xFF).toByte(),
                    ((delay and 0xFF00) shr 8).toByte()
                )
            )
        }

        private fun setBridgeLED(r: Int, g: Int, b: Int) {
            val bleHeader = BLEHeader()
            bleHeader.cmdSize = 4
            bleHeader.cmdFlags = BLECommandFlags.SETTINGS.value or BLESettings.LED_COLOR.value
            mWriteQueue.add(
                bleHeader.toByteArray() + byteArrayOf(
                    (b and 0xFF).toByte(),
                    (r and 0xFF).toByte(),
                    (g and 0xFF).toByte(),
                    0x00.toByte()
                )
            )
        }

        private fun setBridgeSTMIN(amount: Int) {
            val bleHeader = BLEHeader()
            bleHeader.cmdSize = 2
            bleHeader.cmdFlags = BLECommandFlags.SETTINGS.value or BLESettings.ISOTP_STMIN.value
            mWriteQueue.add(
                bleHeader.toByteArray() + byteArrayOf(
                    (amount shr 0).toByte(),
                    (amount shr 8).toByte()
                )
            )
        }

        private fun buildBLEFrame(udsCommand: ByteArray): ByteArray {
            val bleHeader = BLEHeader()
            bleHeader.cmdSize = udsCommand.size
            bleHeader.cmdFlags = BLECommandFlags.PER_CLEAR.value
            return bleHeader.toByteArray() + udsCommand
        }

        private fun changeGAPName(gap: String) {
            val bleHeader = BLEHeader()
            bleHeader.cmdSize = gap.length
            bleHeader.cmdFlags = BLECommandFlags.SETTINGS.value or BLESettings.GAP.value
            mWriteQueue.add(bleHeader.toByteArray() + gap.toByteArray())
        }
    }
}
