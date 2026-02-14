package com.example.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // ✅ Search API
    @GET("v1/search.json")
    suspend fun searchCity(
        @Query("key") key: String,
        @Query("q") city: String
    ): List<SearchLocationResponse>


    // ✅ Astronomy API
    @GET("v1/astronomy.json")
    suspend fun getAstronomy(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("dt") date: String
    ): AstronomyResponse
}

