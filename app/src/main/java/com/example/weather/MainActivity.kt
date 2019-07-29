package com.example.weather

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.UI.DailyAdapter
import com.example.weather.UI.HourlyAdapter
import com.example.weather.db.WeatherEntity
import com.example.weather.model.Weather
import com.example.weather.viewModel.WeatherRepo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var weatherRepo: WeatherRepo

    private val MY_PERMISSIONS_REQUEST_COARSE_LOCATION: Int = 23
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val hourlyRecyclerView = findViewById<RecyclerView>(R.id.hourlyRecyclerView)
        val adapter = HourlyAdapter(this)
        hourlyRecyclerView.adapter = adapter
        hourlyRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


        val dailyRecyclerView = findViewById<RecyclerView>(R.id.dailyRecyclerView)
        val dailyAdapter = DailyAdapter(this)
        dailyRecyclerView.adapter = dailyAdapter
        dailyRecyclerView.layoutManager = LinearLayoutManager(this)


        //setup location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        weatherRepo = ViewModelProviders.of(this).get(WeatherRepo::class.java)

        weatherRepo.weatherData.observe(this, Observer { weatherEntity -> weatherEntity?.let { adapter.setData(weatherEntity.hourly)
        dailyAdapter.setData(weatherEntity.daily)}  })

        getLocation()

        val data:LiveData<WeatherEntity> = weatherRepo.getWeatherData(prefs.latitude, prefs.longitude)
        Log.d("MainActivity",""+data.value)


    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
           getLocationPermissions()

        }
        else
        {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->

                    if(location != null && location.latitude != 0.0)
                    {
                            prefs.latitude = location.latitude
                            prefs.longitude = location.longitude
                    }
                    else
                    {
                        getLocation()
                    }

                }
        }
    }

    private fun getLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                showRequestDialog()

            } else {

                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    MY_PERMISSIONS_REQUEST_COARSE_LOCATION)

            }
        } else {
            // Permission has already been granted
            getLocation()
        }
    }

    private fun showRequestDialog() {
        val alertDialog: AlertDialog? = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setMessage(R.string.weather_permission_dialog_message)?.setTitle(R.string.weather_dialog_title)
                    builder.apply {
                        setPositiveButton(R.string.ok,
                            DialogInterface.OnClickListener { dialog, id ->
                                ActivityCompat.requestPermissions(this@MainActivity,
                                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                                    MY_PERMISSIONS_REQUEST_COARSE_LOCATION)
                            })
                    }
                    // Set other dialog properties


                    // Create the AlertDialog
                    builder.create()
                }

        alertDialog?.show()

    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_COARSE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission granted
                    getLocation()
                } else {
                    // permission denied
                    getLocationPermissions()
                }
                return
            }
        }
    }
}
