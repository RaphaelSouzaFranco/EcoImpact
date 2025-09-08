package com.example.ecoimpact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable  // ADICIONE ESTE IMPORT
import androidx.navigation.compose.rememberNavController
import com.example.ecoimpact.composables.Screen
import com.example.ecoimpact.ui.theme.EcoImpactTheme

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
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    nav.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )

        }
    }
}




