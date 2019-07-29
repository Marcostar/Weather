package com.example.weather.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WeatherDao {

    @Query(value = "SELECT * FROM Weather")
    fun hasValues(): LiveData<WeatherEntity>

    @Query(value = "SELECT * FROM Weather WHERE id = $WEATHER_ID")
    fun getWeather(): LiveData<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherEntity)


}