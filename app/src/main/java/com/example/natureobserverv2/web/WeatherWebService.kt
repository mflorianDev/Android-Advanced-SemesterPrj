package com.example.natureobserverv2.web

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherWebService {
    @GET("data/2.5/weather/")
    fun getWeatherInfo(
        // geoCode: {city name},{state code},{country code} -> optional: state code & country code
        // set encoded to true so comma will not get unicoded to hex value in url
        @Query("q", encoded = true) geoCode: String,
        @Query("appid") apiKey: String
    ): Call<WeatherWebEntity>

    //fun qString(q: List<String>) = q.joinToString(",")

    /**
     * Eventuelle andere Requests auch direkt in dieser Klasse definieren.
     * Die Basis URL ist für alle Requests die gleiche!
     */
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