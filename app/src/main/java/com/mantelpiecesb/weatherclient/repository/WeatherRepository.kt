package com.mantelpiecesb.weatherclient.repository

import com.mantelpiecesb.weatherclient.api.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

    fun getWeather() = weatherService.getWeather()

}