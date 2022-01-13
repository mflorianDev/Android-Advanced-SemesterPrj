package com.example.natureobserverv2.web

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("cod", "message")
@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorWeatherWebEntity(
    @JsonProperty("cod") var cod: Int,
    @JsonProperty("message") var message: String,
)
