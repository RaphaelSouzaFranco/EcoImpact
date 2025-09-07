package com.example.ecoimpact

import com.example.ecoimpact.composables.CarbonScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoimpact.ui.theme.EcoImpactTheme

sealed class Screen(val route: String) {
    object Carbon : Screen("carbon")
    object Home : Screen("home")
}

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
    val nav = rememberNavController()
    NavHost(
        navController = nav,
        startDestination = Screen.Carbon.route,
        modifier = modifier
    ) {

        // Tela de cálculo da pegada de carbono
        composable(Screen.Carbon.route) {
            CarbonScreen(
                onLogin = {
                    nav.navigate(Screen.Home.route)
                },
                onNavigateToResult = { distancia, transporte ->
                    // Aqui você pode navegar para outra tela de resultado futuramente
                    nav.navigate("result")
                },
                modifier = Modifier,
                emission = 12.5, // Exemplo
                comparison = listOf(
                    "Carro médio" to 20.0,
                    "Ônibus" to 15.0
                ),
                tips = listOf(
                    "Use transporte público",
                    "Prefira caminhada ou bicicleta"
                )
            )
        }

        // Tela Home
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Welcome to the Home Screen!")
        Button(onClick = { /* Exemplo de ação */ }) {
            Text("Go to next screen")
        }
    }
}
