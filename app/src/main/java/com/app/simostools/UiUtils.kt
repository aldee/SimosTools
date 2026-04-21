package com.app.simostools

import android.content.Context
import android.content.res.Configuration
import android.graphics.Rect

fun getScreenResolution(context: Context): Rect {
    val dp = context.resources.configuration
    val screenSize: Int = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
    val size = when (screenSize) {
        Configuration.SCREENLAYOUT_SIZE_LARGE -> 600
        Configuration.SCREENLAYOUT_SIZE_NORMAL -> 400
        Configuration.SCREENLAYOUT_SIZE_SMALL -> 200
        else -> 0
    }

    return Rect(dp.screenWidthDp, dp.screenHeightDp, size, size)
}
