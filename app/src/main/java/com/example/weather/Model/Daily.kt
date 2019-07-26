package com.example.weather.Model

public class Daily {

    private var data: List<DailyData>? = null

    public class DailyData {
        private var icon: String = ""
        private var temperatureHigh: Double = 0.0
        private var temperatureLow: Double = 0.0
    }
}