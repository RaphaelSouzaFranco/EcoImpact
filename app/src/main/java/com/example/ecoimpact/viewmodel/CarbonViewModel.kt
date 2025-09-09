package com.example.ecoimpact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecoimpact.data.CarbonResponse
import com.example.ecoimpact.network.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarbonViewModel : ViewModel() {

    private val _emissionResult = MutableStateFlow<CarbonResponse?>(null)
    val emissionResult: StateFlow<CarbonResponse?> = _emissionResult

    fun calculateEmission(distance: String, transport: String) {
        viewModelScope.launch {
            try {
                // Mapeia transporte para um UUID de modelo de veículo
                val vehicleModelId = when (transport.lowercase()) {
                    "carro" -> "7268a9b7-17e8-4c8d-acca-57059252afe9" // Corolla 1993
                    "ônibus" -> "bus-model-uuid" // substitua por UUID válido
                    "metrô" -> "subway-model-uuid"
                    "bicicleta" -> "bicycle-model-uuid"
                    else -> "7268a9b7-17e8-4c8d-acca-57059252afe9"
                }

                val body = JsonObject().apply {
                    addProperty("type", "vehicle")
                    addProperty("distance_unit", "km")
                    addProperty("distance_value", distance.toDouble())
                    addProperty("vehicle_model_id", vehicleModelId)
                }


                val response = RetrofitClient.api.estimateEmission(body)

                if (response.isSuccessful) {
                    // atribui apenas o corpo da resposta
                    _emissionResult.value = response.body()
                } else {
                    println("Erro na API: ${response.code()} - ${response.message()}")
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
