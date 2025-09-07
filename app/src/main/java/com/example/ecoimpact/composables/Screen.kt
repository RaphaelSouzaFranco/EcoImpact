package com.example.ecoimpact.composables

sealed class Screen(val route: String) {
    data object Login : Screen(route = "login")
    data object Carbon : Screen(route = "carbon")
    data object Home : Screen(route = "home") // ← Aqui está o novo destino
}
