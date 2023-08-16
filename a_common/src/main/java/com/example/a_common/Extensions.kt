package com.example.a_common

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Double.huminize(): String {
    val diff = this - this.toLong()
    val dec = if (diff >= 0.01 && this <= 9_999_999)
        DecimalFormat("###,###,###,###,###.00", DecimalFormatSymbols(Locale.ENGLISH))
    else
        DecimalFormat("###,###,###,###,###", DecimalFormatSymbols(Locale.ENGLISH))

    var formattedNumber = dec.format(this).replace(",", " ")
    if (formattedNumber.first() == '.') {
        formattedNumber = "0$formattedNumber"
    }
    return formattedNumber
    // return if (diff > 0) String.format("%.2f", this) else this.toLong().toString()
}

fun Long.huminize(): String {
    val diff = System.currentTimeMillis() - this
    val format = "dd.MM.yyyy  HH:mm"
    val formatted = if (diff < 180_000) {
        "hozirgina"
    } else if (diff < 3600_000) {
        "${diff / 60_000} min oldin"
    } else if (diff < 86_400_000) {
        "${diff / 3600_000} soat oldin"
    } else {
        SimpleDateFormat(format, Locale.getDefault()).format(Date(this))
    }
    return formatted
}


fun Long.huminizeForFile() =
    SimpleDateFormat("dd.MM.yyyy  HH:mm:ss", Locale.getDefault()).format(Date(this))

fun Long.huminizeKb(): String {
    val kbyte = 1024
    val mbyte = 1024 * 1024
    val gbyte = 1024 * 1024 * 1024
    val value = if (this < kbyte) "${this} byte"
    else if (this < 700 * kbyte) "${(this.toDouble() / kbyte).roundTen()} Kb"
    else if (this < 700 * mbyte) "${(this.toDouble() / mbyte).roundTen()} Mb"
    else "${(this.toDouble() / gbyte).roundTen()} Gb"
    return value
}
fun Double.roundTen(): String {
    val dec =
        DecimalFormat("###,###,###,###,###.0", DecimalFormatSymbols(Locale.ENGLISH))
    return dec.format(this).replace(",", " ")
}
