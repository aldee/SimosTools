package com.app.simostools.core.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

//Intent constants
enum class RequiredPermissions(val permission: String, val version: Int, val required: Boolean, var result: Int) {
    BT(Manifest.permission.BLUETOOTH, 0, true, PackageManager.PERMISSION_DENIED),
    @RequiresApi(Build.VERSION_CODES.S) BT_ADVERTISE(Manifest.permission.BLUETOOTH_ADVERTISE, Build.VERSION_CODES.S, true, PackageManager.PERMISSION_DENIED),
    @RequiresApi(Build.VERSION_CODES.S) BT_SCAN(Manifest.permission.BLUETOOTH_SCAN, Build.VERSION_CODES.S, true, PackageManager.PERMISSION_DENIED),
    @RequiresApi(Build.VERSION_CODES.S) BT_CONNECT(Manifest.permission.BLUETOOTH_CONNECT, Build.VERSION_CODES.S, true, PackageManager.PERMISSION_DENIED),
    FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION, 0, true, PackageManager.PERMISSION_DENIED),
    COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION, 0, true, PackageManager.PERMISSION_DENIED),
    READ_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE, 0, false, PackageManager.PERMISSION_DENIED),
    WRITE_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE, 0, false, PackageManager.PERMISSION_DENIED),
    READ_MEDIA_IMAGES(Manifest.permission.READ_MEDIA_IMAGES, Build.VERSION_CODES.TIRAMISU, false, PackageManager.PERMISSION_DENIED),
    READ_MEDIA_VIDEO(Manifest.permission.READ_MEDIA_VIDEO, Build.VERSION_CODES.TIRAMISU, false, PackageManager.PERMISSION_DENIED),
    READ_MEDIA_AUDIO(Manifest.permission.READ_MEDIA_AUDIO, Build.VERSION_CODES.TIRAMISU, false, PackageManager.PERMISSION_DENIED),
    POST_NOTIFICATIONS(Manifest.permission.POST_NOTIFICATIONS, Build.VERSION_CODES.TIRAMISU, false, PackageManager.PERMISSION_DENIED),
}
