package com.app.simostools.core.bluetooth

import java.util.UUID

// Constants that indicate the current connection state
enum class BLEConnectionState {
    ERROR,
    NONE,
    CONNECTING,
    CONNECTED;

    var errorMessage: String = ""
    var deviceName: String = ""
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
    START_SCAN,
    STOP_SCAN,
    CONNECT_TO_DEVICE,
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

//BLE settings
const val BLE_GATT_MTU_SIZE           = 512
const val BLE_SCAN_PERIOD             = 5000L
const val BLE_THREAD_PRIORITY         = 5 //Priority (max is 10)

//ISOTP bridge UUIDS
val BLE_CCCD_UUID               = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
val BLE_SERVICE_UUID            = UUID.fromString("0000abf0-0000-1000-8000-00805f9b34fb")
val BLE_DATA_TX_UUID            = UUID.fromString("0000abf1-0000-1000-8000-00805f9b34fb")
val BLE_DATA_RX_UUID            = UUID.fromString("0000abf2-0000-1000-8000-00805f9b34fb")
val BLE_CMD_TX_UUID             = UUID.fromString("0000abf3-0000-1000-8000-00805f9b34fb")
val BLE_CMD_RX_UUID             = UUID.fromString("0000abf4-0000-1000-8000-00805f9b34fb")

//ISOTP bridge BLE header defaults
const val BLE_HEADER_ID               = 0xF1
const val BLE_HEADER_PT               = 0xF2
const val BLE_HEADER_RX               = 0x7E8
const val BLE_HEADER_TX               = 0x7E0
const val BLE_HEADER_DSG_RX           = 0x7E9
const val BLE_HEADER_DSG_TX           = 0x7E1

const val MAX_GAP_LENGTH              = 14

const val CHANNEL_ID                  = "BTService"
const val CHANNEL_NAME                = "BTService"
