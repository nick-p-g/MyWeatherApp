package com.example.myweather.repository

import android.util.Log
import com.example.myweather.database.WeatherDao
import com.example.myweather.models.WeatherEntity
import com.example.myweather.util.TagUtil.TAG

class DatabaseRepository(private val dao: WeatherDao) {

    suspend fun insert(weather: WeatherEntity){
        Log.i(TAG,"inserting weather into DB")
        dao.insertWeather(weather)
    }

    suspend fun getLast(): WeatherEntity {
        Log.i(TAG,"getting last weather from DB")
        return dao.getLastWeather()
    }

}