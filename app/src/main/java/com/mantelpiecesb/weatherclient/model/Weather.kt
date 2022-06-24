package com.mantelpiecesb.weatherclient.model


data class Weather(
    val lat : Double,
    val lon : Double,
    val current: Current,
    val daily: List<Daily>,
)