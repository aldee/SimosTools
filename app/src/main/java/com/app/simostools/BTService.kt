package com.app.simostools

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import com.app.simostools.core.bluetooth.BLE_HEADER_ID
import com.app.simostools.core.bluetooth.CHANNEL_ID
import com.app.simostools.core.bluetooth.CHANNEL_NAME
import com.app.simostools.core.bluetooth.*
import com.app.simostools.core.uds.*
import com.app.simostools.core.utils.*

@SuppressLint("MissingPermission")
class BTService: Service(), BleManagerCallbacks {
    private val TAG = "BTService"

    private lateinit var mBleManager: BleManager
    private var mFinished: Boolean = false
    private var mStarted: Boolean = false

    override fun onCreate() {
        super.onCreate()
        mBleManager = BleManager(this, this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        if(!mFinished) {
            when (intent?.action) {
                BTServiceTask.STOP_SERVICE.toString()       -> doStopService(startId)
                BTServiceTask.START_SERVICE.toString()      -> doStartService()
                BTServiceTask.REQ_STATUS.toString()         -> sendStatus()
                BTServiceTask.START_SCAN.toString()         -> mBleManager.startScan()
                BTServiceTask.STOP_SCAN.toString()          -> mBleManager.stopScanning()
                BTServiceTask.CONNECT_TO_DEVICE.toString()  -> {
                    val address = intent.getStringExtra("deviceAddress")
                    if (address != null) {
                        mBleManager.connect(address)
                    }
                }
                BTServiceTask.DO_DISCONNECT.toString()      -> mBleManager.disconnect()
                BTServiceTask.DO_START_LOG.toString()       -> mBleManager.setTask(UDSTask.LOGGING)
                BTServiceTask.DO_START_FLASH.toString()     -> mBleManager.setTask(UDSTask.FLASHING)
                BTServiceTask.DO_GET_TUNE_INFO.toString()   -> mBleManager.setTask(UDSTask.TUNE_INFO)
                BTServiceTask.DO_GET_INFO.toString()        -> mBleManager.setTask(UDSTask.INFO)
                BTServiceTask.DO_CLEAR_DTC.toString()       -> mBleManager.setTask(UDSTask.DTC_CLEAR)
                BTServiceTask.DO_GET_DTC.toString()         -> mBleManager.setTask(UDSTask.DTC_GET)
                BTServiceTask.DO_SET_ADAPTER.toString()     -> mBleManager.setTask(UDSTask.SET_ADAPTER)
                BTServiceTask.DO_STOP_TASK.toString()       -> mBleManager.stopTask()
                BTServiceTask.FLASH_CONFIRMED.toString()    -> UDSFlasher.setFlashConfirmed(true)
                BTServiceTask.FLASH_CANCELED.toString()     -> UDSFlasher.cancelFlash()
            }
        }

        return if(mFinished) START_NOT_STICKY else START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        mBleManager.destroy()
        super.onDestroy()
    }

    private fun doStopService(startId: Int) {
        mFinished = true
        mBleManager.disconnect()
        UDSLogger.clear()
        PIDs.clear()
        UDSFlasher.clear()
        DebugLog.i(TAG, "Shutting down down service.")
        DebugLog.close()
        stopForeground(true)
        stopSelfResult(startId)
    }

    private fun doStartService() {
        if(!mStarted) {
            mStarted = true
            val serviceChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            getSystemService(NotificationManager::class.java).createNotificationChannel(serviceChannel)

            val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(getText(R.string.app_name))
                .setContentText(getText(R.string.app_name))
                .setSmallIcon(R.drawable.simostools)
                .build()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE)
            } else {
                startForeground(1, notification)
            }
        }
    }

    private fun sendStatus() {
        onConnectionStateChange(mBleManager.mConnectionState)
    }

    // BleManagerCallbacks implementation
    override fun onConnectionStateChange(state: BLEConnectionState) {
        val intentMessage = Intent(GUIMessage.STATE_CONNECTION.toString())
        intentMessage.setPackage(packageName)
        intentMessage.putExtra(GUIMessage.STATE_CONNECTION.toString(), state)
        sendBroadcast(intentMessage)
    }

    override fun onScanResult(name: String, address: String) {
        val intentMessage = Intent(GUIMessage.SCAN_RESULT.toString())
        intentMessage.setPackage(packageName)
        intentMessage.putExtra("deviceName", name)
        intentMessage.putExtra("deviceAddress", address)
        sendBroadcast(intentMessage)
    }

    override fun onTaskStateChanged(task: UDSTask) {
        if(task == UDSTask.LOGGING && UDSLogger.isEnabled()) {
            onLoggingStatusChanged(true)
        } else {
            val intentMessage = Intent(GUIMessage.STATE_TASK.toString())
            intentMessage.setPackage(packageName)
            intentMessage.putExtra(GUIMessage.STATE_TASK.toString(), task)
            sendBroadcast(intentMessage)
        }
    }

    override fun onPacketReceived(packet: ByteArray) {
        val intentMessage = Intent(GUIMessage.READ.toString())
        intentMessage.setPackage(packageName)
        intentMessage.putExtra(GUIMessage.READ.toString(), packet)
        sendBroadcast(intentMessage)
    }

    override fun onLoggingStatusChanged(enabled: Boolean) {
        val intentMessage = Intent(GUIMessage.WRITE_LOG.toString())
        intentMessage.setPackage(packageName)
        intentMessage.putExtra(GUIMessage.WRITE_LOG.toString(), enabled)
        sendBroadcast(intentMessage)
    }

    override fun onFlashInfo(info: String) {
        val intentMessage = Intent(GUIMessage.FLASH_INFO.toString())
        intentMessage.setPackage(packageName)
        intentMessage.putExtra(GUIMessage.FLASH_INFO.toString(), info)
        sendBroadcast(intentMessage)
    }

    override fun onFlashProgress(progress: Int) {
        val showIntent = Intent(GUIMessage.FLASH_PROGRESS_SHOW.toString())
        showIntent.setPackage(packageName)
        showIntent.putExtra(GUIMessage.FLASH_PROGRESS_SHOW.toString(), progress > 0)
        sendBroadcast(showIntent)

        if(progress > 0) {
            val progressIntent = Intent(GUIMessage.FLASH_PROGRESS.toString())
            progressIntent.setPackage(packageName)
            progressIntent.putExtra(GUIMessage.FLASH_PROGRESS.toString(), progress)
            sendBroadcast(progressIntent)
        }
    }

    override fun onFlashConfirmRequired() {
        val intentMessage = Intent(GUIMessage.FLASH_CONFIRM.toString())
        intentMessage.setPackage(packageName)
        sendBroadcast(intentMessage)
    }

    override fun onFlashButtonReset() {
        val intentMessage = Intent(GUIMessage.FLASH_BUTTON_RESET.toString())
        intentMessage.setPackage(packageName)
        sendBroadcast(intentMessage)
    }

    override fun onUtilityInfo(info: String) {
        val intentMessage = Intent(GUIMessage.UTILITY_INFO.toString())
        intentMessage.setPackage(packageName)
        intentMessage.putExtra(GUIMessage.UTILITY_INFO.toString(), info)
        sendBroadcast(intentMessage)
    }
}
