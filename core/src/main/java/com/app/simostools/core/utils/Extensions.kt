package com.app.simostools.core.utils

import android.graphics.Color

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
fun Int.toArray2(): ByteArray = byteArrayOf((this and 0xFF00 shr 8).toByte(), (this and 0xFF).toByte())
fun Long.toColorInt(): Int = (this.toInt() and 0xFFFFFF) or 0xFF000000.toInt()
fun Long.toHex2(): String = "%04x".format(this)
fun Long.toHex4(): String = "%08x".format(this)
fun Long.toHex(): String = "%16x".format(this)
fun Long.toArray2(): ByteArray = byteArrayOf((this and 0xFF00 shr 8).toByte(), (this and 0xFF).toByte())
fun Long.toArray4(): ByteArray = byteArrayOf((this and 0xFF000000 shr 24).toByte(), (this and 0xFF0000 shr 16).toByte(), (this and 0xFF00 shr 8).toByte(), (this and 0xFF).toByte())
fun ByteArray.toHex(): String = joinToString(separator = " ") { eachByte -> "%02x".format(eachByte) }

fun convertFromBCD(byte: Byte): Int {
    return ((byte.toInt() and 0xF0) shr 4) * 10 + (byte.toInt() and 0x0F)
}
