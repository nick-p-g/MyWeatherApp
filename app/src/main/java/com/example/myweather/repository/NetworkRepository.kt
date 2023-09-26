package com.example.myweather.repository

import android.util.Log
import com.example.myweather.models.WeatherResponse
import com.example.myweather.network.WeatherRetrofit
import com.example.myweather.util.DataState
import com.example.myweather.util.TagUtil.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkRepository {
    private val retrofitInstance = WeatherRetrofit.initRetrofit()

    suspend fun getWeather(place: String): Flow<DataState<WeatherResponse>> = flow {
        emit(DataState.Loading)
        try {
            val result = retrofitInstance.getWeather(
                "https://api.openweathermap.org/data/2.5/weather?q=$place&appid=$API_KEY"
            )
            emit(DataState.Success(result))
        }catch (e: Exception){
            Log.e(TAG,"Network call failed: $e")
            emit(DataState.Error(e))
        }
    }

    companion object{
        //This should be hidden in the build config but to save time this works
        private const val API_KEY = "83c31368e39ba007d1cd391b07efa22e"

        private const val WEATHER_ENDPOINT = "data/2.5/weather?q=atlanta&appid=${API_KEY}"
    }
}