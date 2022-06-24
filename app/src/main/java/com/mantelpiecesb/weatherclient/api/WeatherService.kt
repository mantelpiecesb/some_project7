package com.mantelpiecesb.weatherclient.api

import com.mantelpiecesb.weatherclient.model.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/onecall")
    fun getWeather(
        @Query("lat") latitude: Float = 56.5f,
        @Query("lon") longtitude: Float = 84.9667f,
        @Query("units") units: String = "metric",
        @Query("exclude") exclude: String = "hourly,minutely",
        @Query("lang") language: String = "ru",
        @Query("appid") appId: String = "d3783d35cd85c8185743026665074d77",
    ): Single<Weather>

}