package com.app.simostools.core.uds

import com.app.simostools.core.utils.*

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
    VIN("VIN", byteArrayOf(0xf1.toByte(), 0x90.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ODX_IDENTIFIER("ASAM/ODX File Identifier", byteArrayOf(0xF1.toByte(), 0x9E.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ODX_VERSION("ASAM/ODX File Version", byteArrayOf(0xF1.toByte(), 0xA2.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    VEHICLE_SPEED("Vehicle Speed", byteArrayOf(0xF4.toByte(), 0x0D.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    CAL_NUMBER("Calibration Version Numbers", byteArrayOf(0xF8.toByte(), 0x06.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    PART_NUMBER("VW Spare part Number", byteArrayOf(0xF1.toByte(), 0x87.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ASW_VERSION("VW ASW Version", byteArrayOf(0xF1.toByte(), 0x89.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    HW_NUMBER("ECU Hardware Number", byteArrayOf(0xF1.toByte(), 0x91.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    HW_VERSION("ECU Hardware Version Number", byteArrayOf(0xF1.toByte(), 0xA3.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    ENGINE_CODE("Engine Code", byteArrayOf(0xF1.toByte(), 0xAD.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    WORKSHOP_NAME("VW Workshop Name", byteArrayOf(0xF1.toByte(), 0xAA.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return String(response)
        }
    },
    FLASH_STATE("State of Flash Mem", byteArrayOf(0x04.toByte(), 0x05.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    CODE_VALUE("VW Coding Value", byteArrayOf(0x06.toByte(), 0x00.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            return response.toHex()
        }
    },
    WORKSHOP_CODE("Workshop Code", byteArrayOf(0xF1.toByte(), 0x5B.toByte()))
    {
        override fun parseResponse(response: ByteArray): String {
            var parsed = "\n"
            for(i in 0..4){
                parsed += "Block: ${i + 1} : "

                var thisBlock = response.copyOfRange((10 * i + 0), (10 * i + 10))
                parsed += 2000 + convertFromBCD(thisBlock[0])
                parsed += "_" + convertFromBCD(thisBlock[1])
                parsed += "_" + convertFromBCD(thisBlock[2])
                parsed += " : " + thisBlock.copyOfRange(3,4).toHex()
                parsed += " : " + String(thisBlock.copyOfRange(4,8))
                parsed += " : " + thisBlock.copyOfRange(8,9).toHex()

                parsed += "\n"
            }
            return parsed
        }
    };

    abstract fun parseResponse(response: ByteArray): String
}

val TUNE_INFO_PIDS = intArrayOf(8,10,5,13)

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
        return vals[(this.ordinal+1) % vals.size]

    }
}

enum class FLASH_ECU_ACTION(){
    NONE,
    FLASH,
    PATCH,
}

//Logging modes
enum class UDSLoggingMode(val cfgName: String, val addressMin: Long, val addressMax: Long) {
    MODE_22("22", 0x1000.toLong(), 0xFFFFL),
    MODE_3E("3E", 0x10000000.toLong(), 0xFFFFFFFF);

    val key = "UDSLoggingMode"
}

//Delays and timeouts
const val TASK_BUMP_DELAY             = 250
const val TASK_END_DELAY              = 500
const val TASK_END_TIMEOUT            = 3000
const val TIME_OUT_LOGGING            = 10
const val TIME_OUT_DTC                = 20
const val TIME_OUT_FLASH              = 10
const val TIME_OUT_INFO               = 10

const val CAL_BLOCK_TRANSFER_SIZE = 0xFFD

const val MAX_PIDS                    = 100

//TQ/HP Calculations
const val KG_TO_N                     = 9.80665f
const val TQ_CONSTANT                 = 16.3f

//Max CSV size for log viewer
const val MAX_LOG_SIZE                = 2097152

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
){
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
    ){
        override fun patchTransferSize(address: Int): Int {

            if(address < 0x9600)
                return 0x100
            if(address >= 0x9600 && address < 0x9800)
                return 0x8
            if(address >= 0x9800 && address < 0x7DD00)
                return 0x100
            if(address >= 0x7DD00 && address < 0x7E200)
                return 0x8
            if(address >= 0x7E200 && address < 0x7F900)
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
    ){
        override fun patchTransferSize(address: Int): Int {

            if(address < 0x5cb00)
                return 0x100
            if(address >= 0x5cb00 && address < 0x5cc00)
                return 0x8
            if(address >= 0x5cc00 && address < 0xb3000)
                return 0x100
            if(address >= 0xb3000 && address < 0xb3100)
                return 0x8
            if(address >= 0xb3100 && address < 0xdfb000)
                return 0x100

            return 0x8
        }
    };

    abstract fun patchTransferSize(address: Int): Int
}

enum class COMPATIBLE_BOXCODE_VERSIONS(val str: String, val allowedBoxCodes: Array<String>, val boxCodeLocation: IntArray, val ecm3Range: IntArray, val software: SIMOS_18) {
    _UNDEFINED("UNDEFINED", arrayOf(""), intArrayOf(0x0, 0x01), intArrayOf(0,0), SIMOS_18._1),
    _5G0906259L("5G0906259L", arrayOf("5G0906259A", "5G0906259D", "5G0906259L"), intArrayOf(0x60, 0x6B), intArrayOf(55724,66096), SIMOS_18._1),
    _8V0906264M("8V0906264M", arrayOf("8V0906264M"), intArrayOf(0x60, 0x6B), intArrayOf(55724,66096), SIMOS_18._1),
    _8V0906259K("8V0906259K", arrayOf("5G0906259B", "5G0906259E", "5G0906259H", "5G0906259K"), intArrayOf(0x60, 0x6B), intArrayOf(55724,66096), SIMOS_18._1),
    _8V0906259H("8V0906259H", arrayOf(""), intArrayOf(0x60, 0x6B), intArrayOf(55112,65400), SIMOS_18._1),
    _8V0906259H_PATCH("8V0906259H_PATCH", arrayOf("5G0906259A", "5G0906259D", "5G0906259L", "8V0906264M", "8V0906259B", "8V0906259E", "8V0906259H", "8V0906259K"), intArrayOf(0x60, 0x6B), intArrayOf(55112,65400), SIMOS_18._1),
    _5G0906259Q("5G0906259Q", arrayOf("5G0906259Q", "06K907425J"), intArrayOf(0x60, 0x6B), intArrayOf(59916,72756), SIMOS_18._10),
    _8V0906259Q("8V0906259Q", arrayOf("8V0906259Q", "06K907425J"), intArrayOf(0x60, 0x6B), intArrayOf(59916,72756), SIMOS_18._10),
    _5G0906259Q_PATCH("5G0906259Q_PATCH", arrayOf("5G0906259Q", "06K907425J"), intArrayOf(0x60, 0x6B), intArrayOf(60140,73072), SIMOS_18._10),
}
