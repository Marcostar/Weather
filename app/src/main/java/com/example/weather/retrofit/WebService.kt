package com.example.weather.retrofit


import android.provider.ContactsContract
import com.example.weather.model.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path


interface WebService {

    @GET("f21aeb147d2831c46a7d77a9f2d78e6a/{latitude},{longitude}?exclude=minutely,alerts,flags")
    fun getWeather(@Path(value = "latitude") latitude: Double, @Path(value = "longitude") longitude: Double): Call<Weather>

}