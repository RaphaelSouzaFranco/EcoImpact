package com.example.ecoimpact.composables

sealed class Screen (val route: String){
    data object Login: Screen ("login")
    data object Carbon: Screen ("carbon")
}