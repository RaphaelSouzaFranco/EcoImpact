package com.example.ecoimpact.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: CarbonApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://carbon.free.beeceptor.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarbonApi::class.java)
    }
}
