package com.app.simostools.core.io

import android.graphics.Color

//Color List
enum class ColorList(var value: Int, val cfgName: String) {
    BG_NORMAL(Color.rgb(0, 0, 0), "BGNormal"),
    BG_WARN(Color.rgb(127, 127, 255),"BGWarn"),
    TEXT(Color.rgb(255,   255,   255), "Text"),
    GAUGE_NORMAL(Color.rgb(0,   255, 0), "GaugeNormal"),
    GAUGE_WARN(Color.rgb(255, 0,   0), "GaugeWarn"),
    GAUGE_BG(Color.rgb(0, 0,   0), "GaugeBG"),
    GAUGE_VALUE(Color.rgb(255, 255,   255), "GaugeValue"),
    ST_ERROR(Color.rgb(255, 32,   0), "StateError"),
    ST_NONE(Color.rgb(64, 0,   0), "StateNone"),
    ST_CONNECTING(Color.rgb(255, 128, 0), "StateConnecting"),
    ST_CONNECTED(Color.rgb(0,   128,   0), "StateConnected"),
    ST_LOGGING(Color.rgb(32, 255, 0), "StateLogging"),
    ST_WRITING(Color.rgb(128,   0, 255), "StateWriting"),
    BT_RIM(Color.rgb(64, 64, 64), "ButtonRIm"),
    BT_RIM_ALERT(Color.rgb(255,204, 0), "ButtonRimAlert"),
    BT_TEXT(Color.rgb(255, 255, 255), "ButtonText"),
    BT_BG(Color.rgb(24,   24,   24), "ButtonBG"),
    BT_BG_ALERT(Color.rgb(255,165,0), "ButtonBGAlert");

    val key = "Color"
}
