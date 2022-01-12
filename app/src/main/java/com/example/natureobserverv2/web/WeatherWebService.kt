package com.example.natureobserverv2.web

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherWebService {
    /**
     * Liest alle Kontakte aus dem Web aus mit GET.
     * Die Basis URL wird über den Builder definiert.
     * Der Call wird dann später mit .enqueue aufgerufen und liefert uns das Ergebnis.
     */
    @GET("weather.json")
    fun getWeatherInfo(
        // geoCode: {city name},{state code},{country code} -> optional: state code & country code
        @Query("q") geoCode: String,
        @Query("appid") apiKey: String
    ): Call<List<WeatherWebEntity>>

    /**
     * Eventuelle andere Requests auch direkt in dieser Klasse definieren.
     * Die Basis URL ist für alle Requests die gleiche!
     */
}

/**
 * Erstellt unser Webservice mit hilfe eines Converters (Jackson)
 */
fun createWebService(): WeatherWebService {
    val httpClient = OkHttpClient.Builder()
        // Nachdem die Connection aufgebaut wurde wird 10 Sekunden auf den Response gewartet
        .readTimeout(10, TimeUnit.SECONDS)
        // es wird 10 Sekunden gewartet bis die Connection aufgebaut wird
        .connectTimeout(10, TimeUnit.SECONDS)
        // ansonsten fliegt bei beiden eine Exception wenn .enqueue gerufen wird
        .build()

    /**
     * Baut die Factory für Webservices aus unserem HTTP Client und einem Converter.
     * Die Basis URL wird für alle Requests/Methoden innerhalb des (Web)Interfaces verwendet.
     */
    // TODO: add query to call:   ?q=vienna,at&units=metric&lang=de&appid=d5728be66b249b5ad501e60868036f1f
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/data/2.5/weather/")
        .addConverterFactory(JacksonConverterFactory.create())
        .client(httpClient)
        .build()

    // Retrofit befüllt unser Interface mit Logik mittels Reflection
    return retrofit.create(WeatherWebService::class.java)
}