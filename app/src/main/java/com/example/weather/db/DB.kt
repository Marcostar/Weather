package com.example.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.model.Weather
import com.example.weather.typeConverters.CurrentlyTypeConverters
import com.example.weather.typeConverters.DailyTypeConverters
import com.example.weather.typeConverters.DateTypeConverter
import com.example.weather.typeConverters.HourlyTypeConverters

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [DailyTypeConverters::class, HourlyTypeConverters::class, DateTypeConverter::class, CurrentlyTypeConverters::class])
abstract class DB: RoomDatabase() {
    abstract fun WeatherDao(): WeatherDao

    companion object{
        @Volatile
        private var INSTANCE: DB? = null

        fun getDatabase(context: Context): DB {
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "Weather_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}