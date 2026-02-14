package com.example.api

// ✅ Main Response
data class AstronomyResponse(
    val location: ApiLocation,
    val astronomy: Astronomy
)

// ✅ Location Part (Renamed)
data class ApiLocation(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val localtime: String
)

// ✅ Astronomy Part
data class Astronomy(
    val astro: Astro
)

// ✅ Astro Details
data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String
)

