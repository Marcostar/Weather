package com.example.weather.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weather.Model.Currently
import com.example.weather.Model.Daily
import com.example.weather.TypeConverters.CurrentlyTypeConverters
import com.example.weather.TypeConverters.DailyTypeConverters
import com.example.weather.TypeConverters.HourlyTypeConverters

@Entity(tableName = "Weather")
class WeatherEntity {

    @PrimaryKey
    public var uid: Int? = null


    @ColumnInfo(name = "currently_data")
    @TypeConverters(CurrentlyTypeConverters::class)
    public var currently_data: List<Currently>? = null

    @ColumnInfo(name = "daily_data")
    @TypeConverters(DailyTypeConverters::class)
    public var daily_data: List<Daily.DailyData>? = null

    @ColumnInfo(name = "hourly_data")
    @TypeConverters(HourlyTypeConverters::class)
    public var hourly_data: List<Daily.DailyData>? = null
}