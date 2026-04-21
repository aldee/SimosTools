package com.app.simostools.core.io

import android.os.Environment
import com.app.simostools.core.utils.DebugLog
import com.app.simostools.core.bluetooth.BLE_HEADER_RX
import com.app.simostools.core.bluetooth.BLE_HEADER_TX
import com.app.simostools.core.bluetooth.BLE_HEADER_DSG_RX
import com.app.simostools.core.bluetooth.BLE_HEADER_DSG_TX
import com.app.simostools.core.utils.DEBUG_LOG_INFO
import com.app.simostools.core.utils.DEBUG_LOG_WARNING
import com.app.simostools.core.utils.DEBUG_LOG_EXCEPTION

enum class GearRatios(val gear: String, var ratio: Float) {
    GEAR1("1", 2.92f),
    GEAR2("2",1.79f),
    GEAR3("3",1.14f),
    GEAR4("4",0.78f),
    GEAR5("5",0.58f),
    GEAR6("6",0.46f),
    GEAR7("7",0.0f),
    FINAL("Final",4.77f);

    val key = "GearRatio"
}

enum class DirectoryList(val cfgName: String, val location: String) {
    APP("App",""),
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
        values().forEachIndexed {  i, item ->
            header += item.csvName
            if(i != values().count() - 1)
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
                value = GaugeType.values().find {it.cfgName == newValue} ?: value

            if (value is DirectoryList)
                value = DirectoryList.values().find {it.cfgName == newValue} ?: value

        } catch(e: Exception) {
            DebugLog.e("Settings", "Unable to set $name.", e)
        }
    }
    fun toInt(): Int {
        return if(value is Int) (value as Int) else 0
    }
    fun toFloat(): Float {
        return if(value is Float) (value as Float) else 0f
    }
    fun toDouble(): Double {
        return if(value is Double) (value as Double) else 0.0
    }
    fun toBoolean(): Boolean {
        return if(value is Boolean) (value as Boolean) else false
    }
    fun toGaugeType(): GaugeType {
        return if(value is GaugeType) (value as GaugeType) else GaugeType.ROUND
    }
    fun toDirectory(): DirectoryList {
        return if(value is DirectoryList) (value as DirectoryList) else DirectoryList.APP
    }
    override fun toString(): String {
        if(value is String)
            return value as String

        if(value is Int)
            return (value as Int).toString()

        if(value is Boolean)
            return (value as Boolean).toString()

        if(value is Float)
            return (value as Float).toString()

        if(value is Double)
            return (value as Double).toString()

        if(value is GaugeType)
            return (value as GaugeType).toString()

        if(value is DirectoryList)
            return (value as DirectoryList).toString()

        return name
    }
}
