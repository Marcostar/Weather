package com.example.weather.Model

class Hourly {
    private var data: List<HourlyData>? = null

    public class HourlyData
    {
        private var time: Long = 0
        private var icon: String = ""
        private var temperature: Double = 0.0

    }
}