package com.example.ecoimpact.composables

sealed class Screen (val route: String){
    data object Login: Screen ("login")
    data object Dashboard: Screen ("dashboard")
    data object Transporte: Screen ("transporte")
    data object Carbon: Screen ("carbon")
    data object Final: Screen ("final")
}