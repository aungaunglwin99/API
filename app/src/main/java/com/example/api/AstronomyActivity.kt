package com.example.api

import android.app.DatePickerDialog
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.api.databinding.ActivityAstronomyBinding
import kotlinx.coroutines.launch
import java.util.*

class AstronomyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAstronomyBinding
    private val apiKey = BuildConfig.API_KEY

    // Yangon Fixed Location
    private val yangonLat = 16.8409
    private val yangonLon = 96.1735

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAstronomyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultCard.visibility = View.GONE

        // Date Picker
        binding.etDate.setOnClickListener { openDatePicker() }
        binding.tilDate.setEndIconOnClickListener {
            binding.etDate.performClick()
        }

        // Search Button
        binding.btnSearch.setOnClickListener {

            val city = binding.etCity.text.toString().trim()
            val date = binding.etDate.text.toString().trim()

            if (city.isEmpty()) {
                binding.etCity.error = "Enter City"
                return@setOnClickListener
            }

            if (date.isEmpty()) {
                binding.etDate.error = "Pick Date"
                return@setOnClickListener
            }

            fetchAstronomy(city, date)
        }
    }

    private fun openDatePicker() {

        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->

                val date =
                    "$selectedDay/${selectedMonth + 1}/$selectedYear"

                binding.etDate.setText(date)
            },
            year, month, day
        )

        dialog.show()
    }

    // ✅ Distance Calculation
    private fun calculateDistance(lat: Double, lon: Double): Double {

        val results = FloatArray(1)

        Location.distanceBetween(
            yangonLat, yangonLon,
            lat, lon,
            results
        )

        return results[0].toDouble() / 1000
    }


    // ✅ Fetch API Data
    private fun fetchAstronomy(city: String, date: String) {

        lifecycleScope.launch {

            try {
                val response = RetrofitClient.api.getAstronomy(
                    key = apiKey,
                    city = city,
                    date = date
                )

                val location = response.location
                val astro = response.astronomy.astro

                val distanceKm =
                    calculateDistance(location.lat, location.lon)

                binding.resultCard.visibility = View.VISIBLE

                binding.tvRegionValue.text =
                    "Region : ${location.region}"

                binding.tvCountryValue.text =
                    "Country : ${location.country}"

                binding.tvLocalTimeValue.text =
                    "Local Time : ${location.localtime}"

                binding.tvDistanceValue.text =
                    "Distance : %.2f km from Yangon".format(distanceKm)

                binding.tvSunriseValue.text =
                    "Sunrise : ${astro.sunrise}"

                binding.tvSunsetValue.text =
                    "Sunset : ${astro.sunset}"

                binding.tvMoonriseValue.text =
                    "Moonrise : ${astro.moonrise}"

                binding.tvMoonsetValue.text =
                    "Moonset : ${astro.moonset}"

            } catch (e: Exception) {
                e.printStackTrace()
                binding.resultCard.visibility = View.GONE
                binding.etCity.error = "City not found or API error"
            }
        }
    }
}







