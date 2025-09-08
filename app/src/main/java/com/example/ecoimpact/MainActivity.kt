package com.example.ecoimpact

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
import com.example.ecoimpact.composables.CarbonScreen
import com.example.ecoimpact.ui.theme.EcoImpactTheme

// Unificando as rotas em uma única sealed class
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object Carbon : Screen("carbon")
    object Home : Screen("home")
    // você ainda pode adicionar a "result" se quiser no futuro
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
        startDestination = Screen.Login.route, // começa pelo login
        modifier = modifier
    ) {
        // Login
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    nav.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Dashboard
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onGoToCarbon = {
                    nav.navigate(Screen.Carbon.route)
                },
                onGoToHome = {
                    nav.navigate(Screen.Home.route)
                }
            )
        }

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

        // Home
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

// Exemplos de telas que faltavam
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login Screen")
        Button(onClick = onLoginSuccess, modifier = Modifier.padding(top = 16.dp)) {
            Text("Login")
        }
    }
}

@Composable
fun DashboardScreen(onGoToCarbon: () -> Unit, onGoToHome: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dashboard Screen")
        Button(onClick = onGoToCarbon, modifier = Modifier.padding(top = 16.dp)) {
            Text("Ir para Carbon")
        }
        Button(onClick = onGoToHome, modifier = Modifier.padding(top = 16.dp)) {
            Text("Ir para Home")
        }
    }
}
