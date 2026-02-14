package com.example.api

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.api.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.btSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        binding.btAstronomy.setOnClickListener {
            startActivity(Intent(this, AstronomyActivity::class.java))
        }

        binding.btCurrent.setOnClickListener {
            startActivity(Intent(this, CurrentActivity::class.java))
        }
    }

}