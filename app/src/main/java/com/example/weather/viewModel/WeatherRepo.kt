package com.example.weather.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weather.DataRepository
import com.example.weather.db.DB
import com.example.weather.db.WeatherEntity
import com.example.weather.model.Weather

class WeatherRepo(application: Application): AndroidViewModel(application) {

    val weatherData: LiveData<WeatherEntity>
    private val repository: DataRepository

    init {
        val weatherDao = DB.getDatabase(application).WeatherDao()
        repository = DataRepository(application)
        weatherData = weatherDao.getWeather()
    }

    fun getWeatherData(lat: Double, lon: Double): LiveData<WeatherEntity> = repository.getWeatherData(lat,lon)

}