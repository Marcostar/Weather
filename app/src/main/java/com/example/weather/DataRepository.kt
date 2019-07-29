package com.example.weather

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.weather.db.DB
import com.example.weather.db.WeatherDao
import com.example.weather.db.WeatherEntity
import com.example.weather.model.Weather
import com.example.weather.retrofit.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

class DataRepository(application: Application){

    val weatherDao: WeatherDao
    val retrofit: Retrofit
    val webService: WebService
    private val executor: Executor? = null

    init {

        weatherDao = DB.getDatabase(application).WeatherDao()
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.darksky.net/forecast/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        webService = retrofit.create(WebService::class.java)
    }

    fun getWeatherData(latitude: Double, longitude: Double): LiveData<WeatherEntity>
    {
            suspend {fetchNewData(latitude, longitude)}
            return weatherDao.getWeather()
    }

    @WorkerThread
    suspend fun fetchNewData(latitude: Double, longitude: Double) {



        webService.getWeather(latitude,longitude).enqueue(object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val weatherEntity = WeatherEntity(response.body()!!.currently,
                    response.body()!!.daily.data, response.body()!!.hourly.data )

                suspend { insertion(weatherEntity) }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.d("Refresh User",""+ t.message)
            }
        })



    }


    suspend fun refreshUser(latitude: Double, longitude: Double) {
        // Runs in a background thread.
        executor!!.execute {

            val response = webService.getWeather(latitude, longitude).execute()

            if(response.isSuccessful)
            {

                val weatherEntity = WeatherEntity(response.body()!!.currently,
                    response.body()!!.daily.data, response.body()!!.hourly.data )

               suspend { insertion(weatherEntity) }


            }
            else
            {
                Log.d("Refresh User","Error Message")
            }

            //userDao.save(response.body()!!)
        }
    }

    @WorkerThread
    private suspend fun insertion(weatherEntity: WeatherEntity) {
        val number = weatherDao.insert(weatherEntity)

        Log.d("DataRepository", ""+number)

    }
}
