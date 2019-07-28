package com.example.weather

import android.app.Application

val prefs: Preferences by lazy {
    StarterApp.prefs!!
}
class StarterApp: Application() {

    companion object {
        var prefs: Preferences? = null
    }

    override fun onCreate() {
        prefs = Preferences(applicationContext)
        super.onCreate()
    }

}