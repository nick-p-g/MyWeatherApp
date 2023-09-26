package com.example.myweather.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.myweather.models.WeatherResponse
import com.example.myweather.models.WeatherUI
import com.example.myweather.repository.DatabaseRepository
import com.example.myweather.repository.NetworkRepository
import com.example.myweather.util.DBMapper
import com.example.myweather.util.DataState
import com.example.myweather.util.TagUtil.TAG
import com.example.myweather.util.UiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {
    //factory wasnt working so this was a hack
    var dbRepository: DatabaseRepository? = null
    private val repository = NetworkRepository()
    private val uiMapper = UiMapper()
    private val dbMapper = DBMapper()

    private var _uiResult: MutableLiveData<WeatherUI> = MutableLiveData()
    val uiResult: LiveData<WeatherUI> = Transformations.map(_uiResult) { it }

    fun searchForPlace(place: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWeather(place).collect {
                when (it) {
                    is DataState.Error -> {
                        //TODO update the UI with an error
                        Log.e(TAG, "Something went Wrong: ${it.exception}")
                    }
                    DataState.Loading -> {
                        //TODO update UI with loading animation

                    }
                    is DataState.Success -> {
                        dbRepository?.insert(dbMapper.mapFromDomain(it.data))
                        updateUI(it.data)
                    }
                }
            }
        }
    }

    fun loadFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = dbRepository?.getLast()
            result?.let {
                updateUI(dbMapper.mapToDomain(it))
            }
        }
    }

    private fun updateUI(data: WeatherResponse) {
        val weatherUI = uiMapper.mapFromDomain(data)
        _uiResult.postValue(weatherUI)
    }


}
//Was getting a an error that I took too much time
//Inheritance from an interface with '@JvmDefault' members is only allowed with -Xjvm-default option
/*
class MainActivityViewModelFactory(private val repository: DatabaseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
