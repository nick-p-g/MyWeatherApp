package com.example.myweather.network

import com.example.myweather.models.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherRetrofit {

    @GET
    suspend fun getWeather(@Url url: String): WeatherResponse

    companion object{
        private var retrofitInstance: WeatherRetrofit? = null

        fun initRetrofit(): WeatherRetrofit = retrofitInstance ?: Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(createLoggerClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherRetrofit::class.java)

        private fun createLoggerClient() = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
        private const val BASE_URL = "https://api.openweathermap.org"
    }



}