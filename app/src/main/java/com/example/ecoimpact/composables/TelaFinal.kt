package com.example.ecoimpact.composables

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoimpact.ui.theme.EcoImpactTheme
import com.example.ecoimpact.composables.EcoImpact



data class HistoricoItem(
    val titulo: String,
    val valor: String,
    val data: String,
    val rota: String
)

@Composable
fun EcoImpact(modifier: Modifier = Modifier) { }

@Composable
fun TelaFinal() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {

        composable("dashboard") {
            DashboardScreen(
                navController = navController,
                co2Semana = 2.4,
                calculos = 12,
                historico = listOf(
                    HistoricoItem("Viagem trabalho", "3.2 kg CO₂", "Hoje", "rota_trabalho"),
                    HistoricoItem("Almoço", "1.8 kg CO₂", "Ontem", "rota_almoco"),
                    HistoricoItem("Viagem SP-RJ", "85.4 kg CO₂", "5 dias atrás", "rota_viagem")
                )
            )
        }

        // Colocar telas futuras
        composable("rota_trabalho") { TelaGenerica("Detalhes da Viagem Trabalho") }
        composable("rota_almoco") { TelaGenerica("Detalhes do Almoço") }
        composable("rota_viagem") { TelaGenerica("Detalhes da Viagem SP-RJ") }
    }
}

// DASHBOARD
@Composable
fun DashboardScreen(
    navController: NavController,
    co2Semana: Double,
    calculos: Int,
    historico: List<HistoricoItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto",
                        tint = Color(0xFF2E7D32),
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Maria Silva", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Eco Iniciante 🌱", color = Color.White.copy(alpha = 0.9f))
            }
        }

        Spacer(modifier = Modifier.height(35.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox("kg CO₂ esta semana", "$co2Semana", Modifier.weight(1f))
            Spacer(modifier = Modifier.width(12.dp))
            InfoBox("Cálculos realizados", "$calculos", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(40.dp))


        Text(
            "Histórico",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))


        Column(verticalArrangement = Arrangement.spacedBy(35.dp)) {
            historico.forEach { item ->
                HistoricoRow(item) {
                    navController.navigate(item.rota)
                }
            }
        }
    }
}

@Composable
fun InfoBox(titulo: String, valor: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6D6D6))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(valor, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF2E7D32))
            Spacer(modifier = Modifier.height(4.dp))
            Text(titulo, fontSize = 14.sp, color = Color.Black)
        }
    }
}

@Composable
fun HistoricoRow(item: HistoricoItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6D6D6))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(item.titulo, fontWeight = FontWeight.Medium)
                Text(item.data, fontSize = 12.sp, color = Color.DarkGray)
            }
            Text(item.valor, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TelaGenerica(titulo: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(titulo, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun TelaFinalPreview() {
    EcoImpactTheme {
        TelaFinal()
    }
}