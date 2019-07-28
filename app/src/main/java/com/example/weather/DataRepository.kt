package com.example.weather

import com.example.weather.db.WeatherDao
import com.example.weather.retrofit.WebService

class DataRepository (private val webService: WebService, private val weatherDao: WeatherDao) {


}