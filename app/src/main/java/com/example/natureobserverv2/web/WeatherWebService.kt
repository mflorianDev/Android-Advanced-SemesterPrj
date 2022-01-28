package com.example.natureobserverv2.web

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherWebService {
    @GET("data/2.5/weather/")
    fun getWeatherInfo(
        // required: {city name}, optional: {state code}, {country code}
        // if more than 1 string set encoded to true so comma will not get unicoded to hex value in url
        // geoCode example: lincoln,ne,us -> Lincoln, Nebraska, US
        @Query("q", encoded = true) geoCode: String,
        // optional: standard || metric || imperial
        @Query("units") units: String,
        // optional, for example: en -> English, de -> German
        @Query("lang") lang: String,
        // required
        @Query("appid") apiKey: String
    ): Call<WeatherWebEntity>
}

fun createWebService(): WeatherWebService {
    val httpClient = OkHttpClient.Builder()
        // Nachdem die Connection aufgebaut wurde wird 10 Sekunden auf den Response gewartet
        .readTimeout(10, TimeUnit.SECONDS)
        // es wird 10 Sekunden gewartet bis die Connection aufgebaut wird
        .connectTimeout(10, TimeUnit.SECONDS)
        // ansonsten fliegt bei beiden eine Exception wenn .enqueue gerufen wird
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/")
        .addConverterFactory(JacksonConverterFactory.create())
        .client(httpClient)
        .build()

    return retrofit.create(WeatherWebService::class.java)
}