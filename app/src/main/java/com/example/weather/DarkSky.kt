package com.example.weather

import com.example.weather.Model.Currently
import retrofit2.Call
import retrofit2.http.GET


interface DarkSky {

    @GET("f21aeb147d2831c46a7d77a9f2d78e6a/42.098701,-75.912537?exclude=minutely,alerts,flags")
    fun getWeather(): Call<Weather>

}