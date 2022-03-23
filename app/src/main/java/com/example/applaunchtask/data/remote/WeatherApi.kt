package com.example.applaunchtask.data.remote

import com.example.applaunchtask.data.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"

        // constants and default values for weather api
        const val APP_ID = "b143bb707b2ee117e62649b358207d3e"
        const val LAT = "12.9082847623315"
        const val LON = "77.65197822993314"
        const val UNIT = "imperial"
    }

    @GET("data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") lat: String = LAT,
        @Query("lon") lon: String = LON,
        @Query("units") units: String = UNIT,
        @Query("appid") appId: String = APP_ID,
    ): WeatherResponseDto
}