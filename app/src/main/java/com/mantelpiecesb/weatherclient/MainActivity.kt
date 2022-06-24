package com.mantelpiecesb.weatherclient

import android.location.Geocoder
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mantelpiecesb.weatherclient.databinding.ActivityMainBinding
import com.mantelpiecesb.weatherclient.ui.adapters.WeatherAdapter
import com.mantelpiecesb.weatherclient.utils.DataBeautifiers.getTemperatureText
import com.mantelpiecesb.weatherclient.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    lateinit var weatherAdapter: WeatherAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()

        viewModel.weatherResponse.observe(this) { weather ->
            binding.currentTemperature.text = getTemperatureText(weather.current.temp)
            val geocoder = Geocoder(this, Locale.Builder().setLanguage("RU").build())
            binding.cityName.text = geocoder.getFromLocation(weather.lat, weather.lon, 1).get(0).locality
            binding.feelingTemperature.text = getTemperatureText(weather.current.feels_like)
            weatherAdapter.differ.submitList(weather.daily)
        }

        viewModel.errorMessage.observe(this) { errorText ->
            Snackbar.make(
                binding.root,
                "Ошибка загрузки: " + errorText,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }




    private fun initAdapter() {
        weatherAdapter = WeatherAdapter()
        binding.dailyAdapter.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,
                false)
        }
    }

    override fun onStop() {
        viewModel.stop()
        super.onStop()
    }
}