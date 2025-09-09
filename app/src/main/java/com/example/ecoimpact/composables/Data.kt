package com.example.ecoimpact.data

import com.google.gson.annotations.SerializedName

data class CarbonResponse(
    val data: CarbonData
)

data class CarbonData(
    val id: String,
    val type: String,
    val attributes: CarbonAttributes
)

data class CarbonAttributes(
    @SerializedName("carbon_kg")
    val carbonKg: Double,

    @SerializedName("carbon_lb")
    val carbonLb: Double,

    @SerializedName("distance_unit")
    val distanceUnit: String,

    @SerializedName("distance_value")
    val distanceValue: Double,

    @SerializedName("vehicle_model")
    val vehicleModel: String?
)
