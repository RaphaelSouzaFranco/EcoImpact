package com.example.ecoimpact.network

import com.example.ecoimpact.data.CarbonResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CarbonApi {

    @Headers(
        "Authorization: Bearer 4RXbTPsB9jazBQK6l8gaWA",
        "Content-Type: application/json"
    )
    @POST("estimates")
    suspend fun estimateEmission(@Body body: JsonObject): retrofit2.Response<CarbonResponse>}