package com.example.weather.retrofit


import com.example.weather.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface WebService {

    @GET("f21aeb147d2831c46a7d77a9f2d78e6a/{latitude},{longitude}?exclude=minutely,alerts,flags")
    suspend fun getWeather(@Path(value = "latitude") latitude: Long, @Path(value = "longitude") longitude: Long): Call<Weather>

}