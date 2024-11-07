package com.app.simostools

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import java.util.UUID

// Message types sent from the BluetoothChatService Handler
enum class GUIMessage {
    STATE_CONNECTION,
    STATE_TASK,
    TOAST,
    READ,
    READ_LOG,
    WRITE_LOG,
    UTILITY_INFO,
    UTILITY_INFO_CLEAR,
    UTILITY_PROGRESS,
    UTILITY_PROGRESS_MAX,
    UTILITY_PROGRESS_SHOW,
    FLASH_INFO,
    FLASH_INFO_CLEAR,
    FLASH_PROGRESS,
    FLASH_PROGRESS_MAX,
    FLASH_PROGRESS_SHOW,
    FLASH_CONFIRM,
    FLASH_BUTTON_RESET,
    PID_RELOAD
}

// Constants that indicate the current connection state
enum class BLEConnectionState {
    ERROR,
    NONE,
    CONNECTING,
    CONNECTED;

    var errorMessage: String = ""
    var deviceName: String = ""
}

//List of available tasks
enum class UDSTask {
    NONE,
    LOGGING,
    FLASHING,
    TUNE_INFO,
    INFO,
    DTC_GET,
    DTC_CLEAR,
    SET_ADAPTER
}

//BT functions
enum class BTServiceTask {
    STOP_SERVICE,
    START_SERVICE,
    REQ_STATUS,
    DO_CONNECT,
    DO_DISCONNECT,
    DO_START_LOG,
    DO_START_FLASH,
    DO_GET_TUNE_INFO,
    DO_GET_INFO,
    DO_GET_DTC,
    DO_CLEAR_DTC,
    DO_SET_ADAPTER,
    DO_STOP_TASK,
    FLASH_CONFIRMED,
    FLASH_CANCELED,
}

//Intent constants
enum class RequiredPermissions(
    val permission: String,
    val version: Int,
    val required: Boolean,
    var result: Int
) {
    BT(Manifest.permission.BLUETOOTH, 0, true, PackageManager.PERMISSION_DENIED),
    @RequiresApi(Build.VERSION_CODES.S)
    BT_ADVERTISE(
        Manifest.permission.BLUETOOTH_ADVERTISE,
        Build.VERSION_CODES.S,
        true,
        PackageManager.PERMISSION_DENIED
    ),
    @RequiresApi(Build.VERSION_CODES.S)
    BT_SCAN(
        Manifest.permission.BLUETOOTH_SCAN,
        Build.VERSION_CODES.S,
        true,
        PackageManager.PERMISSION_DENIED
    ),
    @RequiresApi(Build.VERSION_CODES.S)
    BT_CONNECT(
        Manifest.permission.BLUETOOTH_CONNECT,
        Build.VERSION_CODES.S,
        true,
        PackageManager.PERMISSION_DENIED
    ),
    FINE_LOCATION(
        Manifest.permission.ACCESS_FINE_LOCATION,
        0,
        true,
        PackageManager.PERMISSION_DENIED
    ),
    COARSE_LOCATION(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        0,
        true,
        PackageManager.PERMISSION_DENIED
    ),
    READ_STORAGE(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        0,
        false,
        PackageManager.PERMISSION_DENIED
    ),
    WRITE_STORAGE(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        0,
        false,
        PackageManager.PERMISSION_DENIED
    ),
}

//ISOTP bridge command flags
enum class BLECommandFlags(val value: Int) {
    PER_ENABLE(1),
    PER_CLEAR(2),
    PER_ADD(4),
    SPLIT_PK(8),
    SET_GET(64),
    SETTINGS(128)
}

//ISOTP bridge internal settings
enum class BLESettings(val value: Int) {
    ISOTP_STMIN(1),
    LED_COLOR(2),
    PERSIST_DELAY(3),
    PERSIST_Q_DELAY(4),
    BLE_SEND_DELAY(5),
    BLE_MULTI_DELAY(6),
    PASSWORD(7),
    GAP(8)
}

// UDS return codes
enum class UDSReturn {
    OK,
    COMPLETE,
    ERROR_RESPONSE,
    ERROR_NULL,
    ERROR_HEADER,
    ERROR_CMDSIZE,
    ERROR_TIME_OUT,
    ERROR_UNKNOWN,
    COMMAND_QUEUED,
    CLEAR_DTC_REQUEST,
    FLASH_CONFIRM,
    ABORTED,
    FLASH_COMPLETE
}

enum class ECUInfo(val str: String, val address: ByteArray) {
    VIN("VIN", byteArrayOf(0xf1.toByte(), 0x90.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ODX_IDENTIFIER("ASAM/ODX File Identifier", byteArrayOf(0xF1.toByte(), 0x9E.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ODX_VERSION("ASAM/ODX File Version", byteArrayOf(0xF1.toByte(), 0xA2.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    VEHICLE_SPEED("Vehicle Speed", byteArrayOf(0xF4.toByte(), 0x0D.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    CAL_NUMBER("Calibration Version Numbers", byteArrayOf(0xF8.toByte(), 0x06.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    PART_NUMBER("VW Spare part Number", byteArrayOf(0xF1.toByte(), 0x87.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ASW_VERSION("VW ASW Version", byteArrayOf(0xF1.toByte(), 0x89.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    HW_NUMBER("ECU Hardware Number", byteArrayOf(0xF1.toByte(), 0x91.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    HW_VERSION("ECU Hardware Version Number", byteArrayOf(0xF1.toByte(), 0xA3.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ENGINE_CODE("Engine Code", byteArrayOf(0xF1.toByte(), 0xAD.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    WORKSHOP_NAME("VW Workshop Name", byteArrayOf(0xF1.toByte(), 0xAA.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    FLASH_STATE("State of Flash Mem", byteArrayOf(0x04.toByte(), 0x05.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    CODE_VALUE("VW Coding Value", byteArrayOf(0x06.toByte(), 0x00.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    WORKSHOP_CODE("Workshop Code", byteArrayOf(0xF1.toByte(), 0x5B.toByte())) {
        override fun parseResponse(response: ByteArray): String {
            var parsed = "\n"
            for (i in 0..4) {
                parsed += "Block: ${i + 1} : "

                var thisBlock = response.copyOfRange((10 * i + 0), (10 * i + 10))
                parsed += 2000 + convertFromBCD(thisBlock[0])
                parsed += "_" + convertFromBCD(thisBlock[1])
                parsed += "_" + convertFromBCD(thisBlock[2])
                parsed += " : " + thisBlock.copyOfRange(3, 4).toHex()
                parsed += " : " + String(thisBlock.copyOfRange(4, 8))
                parsed += " : " + thisBlock.copyOfRange(8, 9).toHex()




                parsed += "\n"
            }
            return parsed
        }
    };

    abstract fun parseResponse(response: ByteArray): String
}

val TUNE_INFO_PIDS = intArrayOf(8, 10, 5, 13)

enum class FLASH_ECU_CAL_SUBTASK {
    PATCH_BLOCK,
    NONE,
    GET_ECU_BOX_CODE,
    CHECK_FILE_COMPAT,
    CONFIRM_PROCEED,
    CHECKSUM_BIN,
    COMPRESS_BIN,
    ENCRYPT_BIN,
    CLEAR_DTC,
    CHECK_PROGRAMMING_PRECONDITION,
    OPEN_EXTENDED_DIAGNOSTIC,
    SA2SEEDKEY,
    WRITE_WORKSHOP_LOG,
    FLASH_BLOCK,
    CHECKSUM_BLOCK,
    VERIFY_PROGRAMMING_DEPENDENCIES,
    RESET_ECU;

    fun next(): FLASH_ECU_CAL_SUBTASK {
        val vals = values()
        return vals[(this.ordinal + 1) % vals.size]

    }
}

enum class FLASH_ECU_ACTION() {
    NONE,
    FLASH,
    PATCH,
}

//Color List
enum class ColorList(var value: Int, val cfgName: String) {
    BG_NORMAL(Color.rgb(64, 64, 64), "BGNormal"),
    BG_WARN(Color.rgb(127, 127, 255), "BGWarn"),
    TEXT(Color.rgb(255, 255, 255), "Text"),
    GAUGE_NORMAL(Color.rgb(0, 255, 0), "GaugeNormal"),
    GAUGE_WARN(Color.rgb(255, 0, 0), "GaugeWarn"),
    GAUGE_BG(Color.rgb(0, 0, 0), "GaugeBG"),
    GAUGE_VALUE(Color.rgb(255, 255, 255), "GaugeValue"),
    ST_ERROR(Color.rgb(255, 32, 0), "StateError"),
    ST_NONE(Color.rgb(255, 0, 0), "StateNone"),
    ST_CONNECTING(Color.rgb(255, 32, 32), "StateConnecting"),
    ST_CONNECTED(Color.rgb(0, 255, 0), "StateConnected"),
    ST_LOGGING(Color.rgb(32, 255, 0), "StateLogging"),
    ST_WRITING(Color.rgb(0, 0, 255), "StateWriting"),
    BT_RIM(Color.rgb(110, 140, 255), "ButtonRIm"),
    BT_RIM_ALERT(Color.rgb(255, 204, 0), "ButtonRimAlert"),
    BT_TEXT(Color.rgb(255, 255, 255), "ButtonText"),
    BT_BG(Color.rgb(64, 64, 64), "ButtonBG"),
    BT_BG_ALERT(Color.rgb(255, 165, 0), "ButtonBGAlert");

    val key = "Color"
}

//Logging modes
enum class UDSLoggingMode(val cfgName: String, val addressMin: Long, val addressMax: Long) {
    MODE_22("22", 0x1000.toLong(), 0xFFFFL),
    MODE_3E("3E", 0x10000000.toLong(), 0xFFFFFFFF);

    val key = "UDSLoggingMode"
}

enum class GearRatios(val gear: String, var ratio: Float) {
    GEAR1("1", 2.92f),
    GEAR2("2", 1.79f),
    GEAR3("3", 1.14f),
    GEAR4("4", 0.78f),
    GEAR5("5", 0.58f),
    GEAR6("6", 0.46f),
    GEAR7("7", 0.0f),
    FINAL("Final", 4.77f);

    val key = "GearRatio"
}

enum class DirectoryList(val cfgName: String, val location: String) {
    APP("App", ""),
    DOWNLOADS("Downloads", Environment.DIRECTORY_DOWNLOADS),
    DOCUMENTS("Documents", Environment.DIRECTORY_DOCUMENTS);
}

enum class GaugeType(val cfgName: String) {
    BAR_H("BarHorizontal"),
    BAR_V("BarVertical"),
    BASIC("Basic"),
    ROUND("Round")
}

enum class CSVItems(val csvName: String) {
    NAME("Name"),
    UNIT("Unit"),
    EQUATION("Equation"),
    FORMAT("Format"),
    ADDRESS("Address"),
    LENGTH("Length"),
    SIGNED("Signed"),
    PROG_MIN("ProgMin"),
    PROG_MAX("ProgMax"),
    WARN_MIN("WarnMin"),
    WARN_MAX("WarnMax"),
    SMOOTHING("Smoothing"),
    ENABLED("Enabled"),
    TABS("Tabs"),
    ASSIGN_TO("Assign To");

    fun getHeader(): String {
        var header = ""
        values().forEachIndexed { i, item ->
            header += item.csvName
            if (i != values().count() - 1)
                header += ","
        }

        return header
    }
}

enum class ConfigSettings(val cfgName: String, var value: Any) {
    KEEP_SCREEN_ON("KeepScreenOn", true),
    INVERT_CRUISE("InvertCruise", false),
    DISPLAY_RATE("DisplayRate", 15),
    LOGGING_RATE("LoggingRate", 50),
    Q_CORRECTION("QCorrection", 10),
    CALCULATE_HP("CalculateHP", true),
    USE_MS2("UseMS2Torque", true),
    TIRE_DIAMETER("TireDiameter", 0.632f),
    CURB_WEIGHT("CurbWeight", 1500f),
    DRAG_COEFFICIENT("DragCoefficient", 0.000005),
    ALWAYS_PORTRAIT("AlwaysPortrait", false),
    OUT_DIRECTORY("OutputDirectory", DirectoryList.DOWNLOADS),
    GAUGE_TYPE("GaugeType", GaugeType.BASIC),
    DRAW_MIN_MAX("DrawMinMax", false),
    DRAW_GRADUATIONS("DrawGraduations", false),
    DEBUG_LOG("DebugMode", DEBUG_LOG_INFO or DEBUG_LOG_WARNING or DEBUG_LOG_EXCEPTION),
    AUTO_LOG("AutoLog", false),
    LOG_NAME("LogName", "simostools"),
    LOG_SUB_FOLDER("LogSubFolder", ""),
    LOG_DSG("LogDSG", false),
    ADAPTER_NAME("AdapterName", "BLE_TO_ISOTP20");

    fun set(newValue: String) {
        try {
            if (value is String)
                value = newValue

            if (value is Int)
                value = newValue.toInt()

            if (value is Boolean)
                value = newValue.toBoolean()

            if (value is Float)
                value = newValue.toFloat()

            if (value is Double)
                value = newValue.toDouble()

            if (value is GaugeType)
                value = GaugeType.values().find { it.cfgName == newValue } ?: value

            if (value is DirectoryList)
                value = DirectoryList.values().find { it.cfgName == newValue } ?: value

        } catch (e: Exception) {
            DebugLog.e("Settings", "Unable to set $name.", e)
        }
    }

    fun toInt(): Int {
        return if (value is Int) (value as Int) else 0
    }

    fun toFloat(): Float {
        return if (value is Float) (value as Float) else 0f
    }

    fun toDouble(): Double {
        return if (value is Double) (value as Double) else 0.0
    }

    fun toBoolean(): Boolean {
        return if (value is Boolean) (value as Boolean) else false
    }

    fun toGaugeType(): GaugeType {
        return if (value is GaugeType) (value as GaugeType) else GaugeType.ROUND
    }

    fun toDirectory(): DirectoryList {
        return if (value is DirectoryList) (value as DirectoryList) else DirectoryList.APP
    }

    override fun toString(): String {
        if (value is String)
            return value as String

        if (value is Int)
            return (value as Int).toString()

        if (value is Boolean)
            return (value as Boolean).toString()

        if (value is Float)
            return (value as Float).toString()

        if (value is Double)
            return (value as Double).toString()

        if (value is GaugeType)
            return (value as GaugeType).toString()

        if (value is DirectoryList)
            return (value as DirectoryList).toString()

        return name
    }
}

//Delays and timeouts
val TASK_BUMP_DELAY = 250
val TASK_END_DELAY = 500
val TASK_END_TIMEOUT = 3000
val TIME_OUT_LOGGING = 10
val TIME_OUT_DTC = 20
val TIME_OUT_FLASH = 10
val TIME_OUT_INFO = 10

val LAYOUT_NAME = "LAYOUT_NAME"
val MAX_GAP_LENGTH = 14

//Service info
val CHANNEL_ID = "BTService"
val CHANNEL_NAME = "BTService"

//BLE settings
val BLE_GATT_MTU_SIZE = 512
val BLE_SCAN_PERIOD = 5000L
val BLE_THREAD_PRIORITY = 5 //Priority (max is 10)

//ISOTP bridge UUIDS
val BLE_CCCD_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
val BLE_SERVICE_UUID = UUID.fromString("0000abf0-0000-1000-8000-00805f9b34fb")
val BLE_DATA_TX_UUID = UUID.fromString("0000abf1-0000-1000-8000-00805f9b34fb")
val BLE_DATA_RX_UUID = UUID.fromString("0000abf2-0000-1000-8000-00805f9b34fb")
val BLE_CMD_TX_UUID = UUID.fromString("0000abf3-0000-1000-8000-00805f9b34fb")
val BLE_CMD_RX_UUID = UUID.fromString("0000abf4-0000-1000-8000-00805f9b34fb")

//ISOTP bridge BLE header defaults
val BLE_HEADER_ID = 0xF1
val BLE_HEADER_PT = 0xF2
val BLE_HEADER_RX = 0x7E8
val BLE_HEADER_TX = 0x7E0
val BLE_HEADER_DSG_RX = 0x7E9
val BLE_HEADER_DSG_TX = 0x7E1

//Log files
val DEBUG_LOG_NONE = 0
val DEBUG_LOG_INFO = 1
val DEBUG_LOG_WARNING = 2
val DEBUG_LOG_DEBUG = 4
val DEBUG_LOG_EXCEPTION = 8
val DEBUG_LOG_COMMUNICATIONS = 16

//TQ/HP Calculations
val KG_TO_N = 9.80665f
val TQ_CONSTANT = 16.3f

//Max allowed PID count
val MAX_PIDS = 100

//Max CSV size for log viewer
val MAX_LOG_SIZE = 2097152


val CAL_BLOCK_TRANSFER_SIZE = 0xFFD


enum class SIMOS_18(
    val version: String,
    val baseAddresses: UIntArray,
    val blockLengths: IntArray,
    val fullBinLocations: IntArray,
    val blockNumberMap: IntArray,
    val checksumLocations: IntArray,
    val sa2Script: ByteArray,
    val cryptoKey: ByteArray,
    val cryptoIV: ByteArray,
    val patchBlockNum: Int,
) {
    _1(
        "Simos 18.1",
        //baseAddresses
        uintArrayOf(
            (0x80000000).toUInt(),  // SBOOT
            (0x8001C000).toUInt(),  // CBOOT
            (0x80040000).toUInt(),  // ASW1
            (0x80140000).toUInt(),  // ASW2
            (0x80880000).toUInt(),  // ASW3
            (0xA0800000).toUInt(),  // CAL
            (0x80840000).toUInt(),  // CBOOT_temp
        ),
        //blockLengths
        intArrayOf(
            0x0,      //SBOOT, we don't care but this way things line up.
            0x23E00,  // CBOOT
            0xFFC00,  // ASW1
            0xBFC00,  // ASW2
            0x7FC00,  // ASW3
            0x7FC00,  // CAL
            0x23E00,  // CBOOT_temp
        ),
        //fullBinLocations
        intArrayOf(
            0x0,        //SBOOT, we don't care.... but
            0x1c000,    //CBOOT
            0x40000,    //ASW1
            0x140000,   //ASW2
            0x280000,   //ASW3
            0x200000,   //CAL
        ),
        //blockNUmberMap
        intArrayOf(
            0, 1, 2, 3, 4, 5
        ),
        //ChecksumLocations
        intArrayOf(
            0x300,
            0x300,
            0x300,
            0x0,
            0x0,
            0x300,

            ),
        //SA2 Script:
        byteArrayOf(
            0x68.toByte(),
            0x2.toByte(),
            0x81.toByte(),
            0x4a.toByte(),
            0x10.toByte(),
            0x68.toByte(),
            0x4.toByte(),
            0x93.toByte(),
            0x8.toByte(),
            0x8.toByte(),
            0x20.toByte(),
            0x9.toByte(),
            0x4a.toByte(),
            0x5.toByte(),
            0x87.toByte(),
            0x22.toByte(),
            0x12.toByte(),
            0x19.toByte(),
            0x54.toByte(),
            0x82.toByte(),
            0x49.toByte(),
            0x93.toByte(),
            0x7.toByte(),
            0x12.toByte(),
            0x20.toByte(),
            0x11.toByte(),
            0x82.toByte(),
            0x4a.toByte(),
            0x5.toByte(),
            0x87.toByte(),
            0x3.toByte(),
            0x11.toByte(),
            0x20.toByte(),
            0x10.toByte(),
            0x82.toByte(),
            0x4a.toByte(),
            0x1.toByte(),
            0x81.toByte(),
            0x49.toByte(),
            0x4c.toByte(),
        ),
        //Crypto Key
        byteArrayOf(
            0x98.toByte(),
            0xD3.toByte(),
            0x12.toByte(),
            0x02.toByte(),
            0xE4.toByte(),
            0x8E.toByte(),
            0x38.toByte(),
            0x54.toByte(),
            0xF2.toByte(),
            0xCA.toByte(),
            0x56.toByte(),
            0x15.toByte(),
            0x45.toByte(),
            0xBA.toByte(),
            0x6F.toByte(),
            0x2F.toByte()
        ),
        //CryptoIV
        byteArrayOf(
            0xE7.toByte(),
            0x86.toByte(),
            0x12.toByte(),
            0x78.toByte(),
            0xC5.toByte(),
            0x08.toByte(),
            0x53.toByte(),
            0x27.toByte(),
            0x98.toByte(),
            0xBC.toByte(),
            0xA4.toByte(),
            0xFE.toByte(),
            0x45.toByte(),
            0x1D.toByte(),
            0x20.toByte(),
            0xD1.toByte()
        ),
        //patchBlockNum
        4,
    ) {
        override fun patchTransferSize(address: Int): Int {

            if (address < 0x9600)
                return 0x100
            if (address >= 0x9600 && address < 0x9800)
                return 0x8
            if (address >= 0x9800 && address < 0x7DD00)
                return 0x100
            if (address >= 0x7DD00 && address < 0x7E200)
                return 0x8
            if (address >= 0x7E200 && address < 0x7F900)
                return 0x100

            return 0x8
        }
    },
    _10(
        "Simos 18.10",
        //baseAddresses
        uintArrayOf(
            (0x80000000).toUInt(),  // SBOOT
            (0x80800000).toUInt(),  // CBOOT
            (0x80020000).toUInt(),  // ASW1
            (0x80100000).toUInt(),  // ASW2
            (0x808C0000).toUInt(),  // ASW3
            (0xA0820000).toUInt(),  // CAL
            (0x80880000).toUInt(),  // CBOOT_temp
        ),
        //blockLengths
        intArrayOf(
            0x0,      //SBOOT, we don't care but this way things line up.
            0x1FE00,  // CBOOT
            0xDFC00,  // ASW1
            0xFFC00,  // ASW2
            0x13FC00,  // ASW3
            0x9FC00,  // CAL
            0x1FE00,  // CBOOT_temp
        ),
        //fullBinLocations
        intArrayOf(
            0x0,        //SBOOT, we don't care.... but
            0x200000,    //CBOOT
            0x20000,    //ASW1
            0x100000,   //ASW2
            0x2c0000,   //ASW3
            0x220000,   //CAL
        ),
        //blockNumberMap
        intArrayOf(
            0, 1, 2, 3, 4, 5
        ),
        //checksumLocaations
        intArrayOf(
            0x300,
            0x300,
            0x300,
            0x0,
            0x0,
            0x300,
        ),
        //sa2script:
        byteArrayOf(
            0x68.toByte(),
            0x3.toByte(),
            0x81.toByte(),
            0x4a.toByte(),
            0x10.toByte(),
            0x68.toByte(),
            0x2.toByte(),
            0x93.toByte(),
            0x5.toByte(),
            0x5.toByte(),
            0x20.toByte(),
            0x15.toByte(),
            0x4a.toByte(),
            0x5.toByte(),
            0x87.toByte(),
            0x22.toByte(),
            0x12.toByte(),
            0x19.toByte(),
            0x54.toByte(),
            0x82.toByte(),
            0x49.toByte(),
            0x93.toByte(),
            0xf4.toByte(),
            0x23.toByte(),
            0xbf.toByte(),
            0x7d.toByte(),
            0x82.toByte(),
            0x4a.toByte(),
            0x5.toByte(),
            0x87.toByte(),
            0x5a.toByte(),
            0x63.toByte(),
            0xfc.toByte(),
            0x5e.toByte(),
            0x82.toByte(),
            0x4a.toByte(),
            0x1.toByte(),
            0x81.toByte(),
            0x49.toByte(),
            0x4c.toByte(),
        ),
        //CryptoKey:
        byteArrayOf(
            0xae.toByte(),
            0x54.toByte(),
            0x5.toByte(),
            0x2.toByte(),
            0xe4.toByte(),
            0x8e.toByte(),
            0x38.toByte(),
            0x54.toByte(),
            0xdb.toByte(),
            0xca.toByte(),
            0x1a.toByte(),
            0x15.toByte(),
            0x45.toByte(),
            0xba.toByte(),
            0x6f.toByte(),
            0x33.toByte(),
        ),
        //CryptoIV:
        byteArrayOf(
            0x62.toByte(),
            0xf3.toByte(),
            0x13.toByte(),
            0xfa.toByte(),
            0x5c.toByte(),
            0x8.toByte(),
            0x53.toByte(),
            0x27.toByte(),
            0x98.toByte(),
            0xbc.toByte(),
            0xa4.toByte(),
            0x52.toByte(),
            0x47.toByte(),
            0x1d.toByte(),
            0x20.toByte(),
            0xd5.toByte(),
        ),
        //patchBlockNum:
        2,
    ) {
        override fun patchTransferSize(address: Int): Int {

            if (address < 0x5cb00)
                return 0x100
            if (address >= 0x5cb00 && address < 0x5cc00)
                return 0x8
            if (address >= 0x5cc00 && address < 0xb3000)
                return 0x100
            if (address >= 0xb3000 && address < 0xb3100)
                return 0x8
            if (address >= 0xb3100 && address < 0xdfb000)
                return 0x100

            return 0x8
        }
    };

    abstract fun patchTransferSize(address: Int): Int
}

enum class COMPATIBLE_BOXCODE_VERSIONS(
    val str: String,
    val allowedBoxCodes: Array<String>,
    val boxCodeLocation: IntArray,
    val ecm3Range: IntArray,
    val software: SIMOS_18
) {
    _UNDEFINED("UNDEFINED", arrayOf(""), intArrayOf(0x0, 0x01), intArrayOf(0, 0), SIMOS_18._1),
    _5G0906259L(
        "5G0906259L",
        arrayOf("5G0906259A", "5G0906259D", "5G0906259L"),
        intArrayOf(0x60, 0x6B),
        intArrayOf(55724, 66096),
        SIMOS_18._1
    ),
    _8V0906264M(
        "8V0906264M",
        arrayOf("8V0906264M"),
        intArrayOf(0x60, 0x6B),
        intArrayOf(55724, 66096),
        SIMOS_18._1
    ),
    _8V0906259K(
        "8V0906259K",
        arrayOf("8V0906259B", "8V0906259E", "8V0906259H", "8V0906259K"),
        intArrayOf(0x60, 0x6B),
        intArrayOf(55724, 66096),
        SIMOS_18._1
    ),
    _8V0906259H(
        "8V0906259H",
        arrayOf(""),
        intArrayOf(0x60, 0x6B),
        intArrayOf(55112, 65400),
        SIMOS_18._1
    ),
    _8V0906259H_PATCH(
        "8V0906259H_PATCH",
        arrayOf(
            "5G0906259A",
            "5G0906259D",
            "5G0906259L",
            "8V0906264M",
            "8V0906259B",
            "8V0906259E",
            "8V0906259H",
            "8V0906259K"
        ),
        intArrayOf(0x60, 0x6B),
        intArrayOf(55112, 65400),
        SIMOS_18._1
    ),
    _5G0906259Q(
        "5G0906259Q",
        arrayOf("5G0906259Q", "06K907425J"),
        intArrayOf(0x60, 0x6B),
        intArrayOf(59916, 72756),
        SIMOS_18._10
    ),
    _8V0906259Q(
        "8V0906259Q",
        arrayOf("8V0906259Q", "06K907425J"),
        intArrayOf(0x60, 0x6B),
        intArrayOf(59916, 72756),
        SIMOS_18._10
    ),
    _5G0906259Q_PATCH(
        "5G0906259Q_PATCH",
        arrayOf("5G0906259Q", "06K907425J"),
        intArrayOf(0x60, 0x6B),
        intArrayOf(60140, 73072),
        SIMOS_18._10
    ),
}

fun getScreenResolution(context: Context): Rect {
    val dp = context.resources.configuration
    val screenSize: Int =
        context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
    val size = when (screenSize) {
        Configuration.SCREENLAYOUT_SIZE_LARGE -> 600
        Configuration.SCREENLAYOUT_SIZE_NORMAL -> 400
        Configuration.SCREENLAYOUT_SIZE_SMALL -> 200
        else -> 0
    }

    return Rect(dp.screenWidthDp, dp.screenHeightDp, size, size)
}

//Additional properties
infix fun Byte.shl(that: Int): Int = this.toInt().shl(that)
infix fun Short.shl(that: Int): Int = this.toInt().shl(that)
infix fun Byte.shr(that: Int): Int = this.toInt().shr(that)
infix fun Short.shr(that: Int): Int = this.toInt().shr(that)
infix fun Byte.and(that: Int): Int = this.toInt().and(that)
infix fun Short.and(that: Int): Int = this.toInt().and(that)
fun Byte.toHex(): String = "%02x".format(this)
fun Byte.toHexS(): String = " %02x".format(this)
fun Short.toHex(): String = "%04x".format(this)
fun Int.toHex(): String = "%08x".format(this)
fun Int.toColorInverse(): Int = Color.WHITE xor this or 0xFF000000.toInt()
fun Int.toColorHex(): String = "%06x".format(this and 0xFFFFFF)
fun Int.toTwo(): String = "%02d".format(this)
fun Int.toArray2(): ByteArray =
    byteArrayOf((this and 0xFF00 shr 8).toByte(), (this and 0xFF).toByte())

fun Long.toColorInt(): Int = (this.toInt() and 0xFFFFFF) or 0xFF000000.toInt()
fun Long.toHex2(): String = "%04x".format(this)
fun Long.toHex4(): String = "%08x".format(this)
fun Long.toHex(): String = "%16x".format(this)
fun Long.toArray2(): ByteArray =
    byteArrayOf((this and 0xFF00 shr 8).toByte(), (this and 0xFF).toByte())

fun Long.toArray4(): ByteArray = byteArrayOf(
    (this and 0xFF000000 shr 24).toByte(),
    (this and 0xFF0000 shr 16).toByte(),
    (this and 0xFF00 shr 8).toByte(),
    (this and 0xFF).toByte()
)

fun ByteArray.toHex(): String =
    joinToString(separator = " ") { eachByte -> "%02x".format(eachByte) }
