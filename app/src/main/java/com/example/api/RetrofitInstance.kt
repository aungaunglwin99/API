package com.example.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.weatherapi.com/"

    val api: ApiService by lazy {

        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                retrofit2.converter.gson.GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}

