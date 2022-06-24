package com.mantelpiecesb.weatherclient.utils

import java.text.SimpleDateFormat
import java.util.*

object DataBeautifiers {
    fun getSimpleDateFormatRU(timestamp: Long, pattern:String) : String {
        return SimpleDateFormat(pattern, Locale.Builder().setLanguage("RU").build()).format(timestamp * 1000)
    }

    fun getTemperatureText(temperature : Double): String {
        return if(temperature > 0) "+" + temperature.toInt().toString() + "°" else "-" + temperature.toInt().toString() + "°"
    }
}