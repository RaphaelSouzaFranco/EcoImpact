package com.example.ecoimpact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoimpact.composables.*
import com.example.ecoimpact.ui.theme.EcoImpactTheme
import com.example.ecoimpact.viewmodel.CarbonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcoImpactTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcoImpact(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EcoImpact(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val carbonViewModel: CarbonViewModel = viewModel() // compartilhado entre telas

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        // --- Login ---
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // --- Dashboard ---
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onGoToTransporte = {
                    navController.navigate(Screen.Transporte.route)
                }
            )
        }

        // --- Transporte ---
        composable(Screen.Transporte.route) {
            TransporteScreen(
                onNavigateToResult = { distancia, transporte ->
                    // Calcula emissão e navega
                    carbonViewModel.calculateEmission(distancia, transporte)
                    navController.navigate(Screen.Carbon.route)
                },
                onLogin = {}
            )
        }

        // --- Carbon ---
        composable(Screen.Carbon.route) {
            CarbonScreen(
                viewModel = carbonViewModel,
                onNext = {
                    navController.navigate(Screen.Final.route)
                }
            )
        }

        // --- Tela Final ---
        composable(Screen.Final.route) {
            TelaFinal()
        }
    }
}
