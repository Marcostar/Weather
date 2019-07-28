package com.example.weather

import android.content.Context
import android.content.SharedPreferences

class Preferences (context: Context) {
    val PREF_NAME = "com.example.weather"
    val ROW_ID = "row_id"
    val LATITUDE = "latitude"
    val LONGITUDE = "longitude"
    val INSERTION_TIME = "insertion_time"
    val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)


    //Get row_number
    var row_number: Long
        get() = prefs.getLong(ROW_ID,0)
        set(value) = prefs.edit().putLong(ROW_ID, value).apply()


    //Get Latitude
    var latitude: Double
        get() = prefs.getDouble(LATITUDE,0.0)
        set(value) = prefs.edit().putDouble(LATITUDE, value).apply()


    //Get Latitude
    var longitude: Double
        get() = prefs.getDouble(LONGITUDE,0.0)
        set(value) = prefs.edit().putDouble(LONGITUDE, value).apply()

    var insertion_time: Long
        get() = prefs.getLong(INSERTION_TIME,0)
        set(value) = prefs.edit().putLong(INSERTION_TIME,value).apply()


    fun SharedPreferences.Editor.putDouble(key: String, double: Double) =
        putLong(key, java.lang.Double.doubleToRawLongBits(double))

    fun SharedPreferences.getDouble(key: String, default: Double) =
        java.lang.Double.longBitsToDouble(getLong(key, java.lang.Double.doubleToRawLongBits(default)))

}