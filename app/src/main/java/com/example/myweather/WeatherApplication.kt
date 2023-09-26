package com.example.myweather

import android.app.Application
import com.example.myweather.database.WeatherDatabase
import com.example.myweather.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeatherApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WeatherDatabase.getDatabase(this) }
    val repository by lazy { DatabaseRepository(database.weatherDao()) }
}