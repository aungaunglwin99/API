package com.example.api

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.api.databinding.ActivitySearchBinding
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.resultCard.visibility = View.GONE

        binding.btSearch.setOnClickListener {

            val city = binding.etCity.text.toString().trim()

            if (city.isEmpty()) {
                binding.etCity.error = "Enter a city"
                return@setOnClickListener
            }

            lifecycleScope.launch {

                try {
                    val result =
                        RetrofitClient.api.searchCity(BuildConfig.API_KEY, city)


                    if (result.isNotEmpty()) {

                        val data = result[0]

                        binding.resultCard.visibility = View.VISIBLE
                        binding.tvName.text = "Name : ${data.name}"
                        binding.tvCountry.text = "Country : ${data.country}"
                        binding.tvLat.text = "Latitude : ${data.lat}"
                        binding.tvLon.text = "Longitude : ${data.lon}"

                    }

                } catch (e: Exception) {
                    binding.etCity.error = "City not found!"
                }
            }
        }
    }
}

