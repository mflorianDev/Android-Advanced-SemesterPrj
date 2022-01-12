package com.example.natureobserverv2.web

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonPropertyOrder(
    "coord",
    "weather",
    "base",
    "main",
    "visibility",
    "wind",
    "clouds",
    "dt",
    "sys",
    "timezone",
    "id",
    "name",
    "cod",
    )
@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherWebEntity(
    @JsonProperty("coord") var coord: Coord,
    @JsonProperty("weather") var weather: List<Weather>,
    @JsonProperty("base") var base: String,
    @JsonProperty("main") var main: Main,
    @JsonProperty("visibility") var visibility: Int,
    @JsonProperty("wind") var wind: Wind,
    @JsonProperty("clouds") var clouds: Clouds,
    @JsonProperty("dt") var dt: Int,
    @JsonProperty("sys") var sys: Sys,
    @JsonProperty("timezone") var timezone: Int,
    @JsonProperty("id") var id: Int,
    @JsonProperty("name") var name: String,
    @JsonProperty("cod") var cod: Int,
    )

@JsonIgnoreProperties(ignoreUnknown = true)
data class Clouds (
    @JsonProperty("all") var all: Int,
    )

@JsonPropertyOrder("lon", "lat")
@JsonIgnoreProperties(ignoreUnknown = true)
data class Coord(
    @JsonProperty("lon") var lon: Float,
    @JsonProperty("lat") var lat: Float
    )

@JsonPropertyOrder(
    "temp",
    "feels_like",
    "temp_min",
    "temp_max",
    "pressure",
    "humidity",
    )
@JsonIgnoreProperties(ignoreUnknown = true)
data class Main (
    @JsonProperty("temp") var temp: Float,
    @JsonProperty("feels_like") var feelsLike: Float,
    @JsonProperty("temp_min") var tempMin: Float,
    @JsonProperty("temp_max") var tempMax: Float,
    @JsonProperty("pressure") var pressure: Int,
    @JsonProperty("humidity") var humidity: Int,
    )


@JsonPropertyOrder(
    "type",
    "id",
    "country",
    "sunrise",
    "sunset",
    )
@JsonIgnoreProperties(ignoreUnknown = true)
data class Sys (
    @JsonProperty("type") var type: Int,
    @JsonProperty("id") var id: Int,
    @JsonProperty("country") var country: String,
    @JsonProperty("sunrise") var sunrise: Int,
    @JsonProperty("sunset") var sunset: Int,
    )


@JsonPropertyOrder(
    "id",
    "main",
    "description",
    "icon",
    )
@JsonIgnoreProperties(ignoreUnknown = true)
data class Weather (
    @JsonProperty("id") var id: Int,
    @JsonProperty("main") var main: String,
    @JsonProperty("description") var description: String,
    @JsonProperty("icon") var icon: String,
    )


@JsonPropertyOrder(
    "speed",
    "deg",
    "gust",
    )
@JsonIgnoreProperties(ignoreUnknown = true)
data class Wind (
    @JsonProperty("speed") var speed: Float,
    @JsonProperty("deg") var deg: Int,
    @JsonProperty("gust") var gust: Float,
    )