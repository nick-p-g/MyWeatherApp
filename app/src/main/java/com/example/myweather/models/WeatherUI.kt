package com.example.myweather.models

data class WeatherUI(
    val base: String,
    val all: Int,
    val cod: Int,
    val lat: Double,
    val lon: Double,
    val dt: Int,
    val id: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val name: String,
    val country: String,
    val sysId: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val deg: Int,
    val gust: Double,
    val speed: Double
)
