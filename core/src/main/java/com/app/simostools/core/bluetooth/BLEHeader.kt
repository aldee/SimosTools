package com.app.simostools.core.bluetooth

import com.app.simostools.core.utils.and

// Header we expect to receive on BLE packets
class BLEHeader {
    var hdID: Int = BLE_HEADER_ID
    var cmdFlags: Int = 0
    var rxID: Int = BLE_HEADER_RX
    var txID: Int = BLE_HEADER_TX
    var cmdSize: Int = 0
    var tickCount: Int = 0

    fun isValid(): Boolean {
        return hdID == BLE_HEADER_ID
    }

    fun toByteArray(): ByteArray {
        val bArray = ByteArray(8)
        bArray[0] = (hdID and 0xFF).toByte()
        bArray[1] = (cmdFlags and 0xFF).toByte()
        bArray[2] = (rxID and 0xFF).toByte()
        bArray[3] = ((rxID and 0xFF00) shr 8).toByte()
        bArray[4] = (txID and 0xFF).toByte()
        bArray[5] = ((txID and 0xFF00) shr 8).toByte()
        bArray[6] = (cmdSize and 0xFF).toByte()
        bArray[7] = ((cmdSize and 0xFF00) shr 8).toByte()

        return bArray
    }

    fun fromByteArray(bArray: ByteArray) {
        hdID = bArray[0] and 0xFF
        cmdFlags = bArray[1] and 0xFF
        rxID = ((bArray[3] and 0xFF) shl 8) + (bArray[2] and 0xFF)
        txID = ((bArray[5] and 0xFF) shl 8) + (bArray[4] and 0xFF)
        cmdSize = ((bArray[7] and 0xFF) shl 8) + (bArray[6] and 0xFF)
        tickCount = ((rxID  and 0xFFFF) shl 16) + (txID  and 0xFFFF)
    }

    fun size(): Int {
        return 8
    }

    fun size_partial(): Int {
        return 2
    }
}
