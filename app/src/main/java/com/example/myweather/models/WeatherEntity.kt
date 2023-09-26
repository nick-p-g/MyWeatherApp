package com.example.myweather.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.*

@Entity(tableName = "WeatherTable")
data class WeatherEntity(
    @ColumnInfo
    val base: String,
    val all: Int,
    val cod: Int,
    val lat: Double,
    val lon: Double,
    val dt: Int,
    @PrimaryKey
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
    val deg: Int,
    val gust: Double,
    val speed: Double,
    val timestamp: Long = Date().time
)
