package com.example.myweather.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweather.models.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM WeatherTable ORDER BY timestamp DESC LIMIT 1 ")
    fun getLastWeather(): WeatherEntity
}