package com.example.api

data class SearchLocationResponse(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double
)
