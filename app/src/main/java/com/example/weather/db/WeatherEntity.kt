package com.example.weather.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weather.model.Currently
import com.example.weather.model.Daily
import com.example.weather.model.DailyData
import com.example.weather.model.HourlyData
import com.example.weather.typeConverters.CurrentlyTypeConverters
import com.example.weather.typeConverters.DailyTypeConverters
import com.example.weather.typeConverters.HourlyTypeConverters

const val WEATHER_ID = 0

@Entity(tableName = "Weather")
data class WeatherEntity(@ColumnInfo(name = "currently_data") @TypeConverters(CurrentlyTypeConverters::class) var currently: Currently,
                         @ColumnInfo(name = "daily_data") @TypeConverters(DailyTypeConverters::class) var daily: List<DailyData>,
                         @ColumnInfo(name = "hourly_data") @TypeConverters(HourlyTypeConverters::class) var hourly: List<HourlyData>){
    @PrimaryKey(autoGenerate = false)
    var id: Int = WEATHER_ID
}








