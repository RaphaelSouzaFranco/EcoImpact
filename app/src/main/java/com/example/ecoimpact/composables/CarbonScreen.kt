package com.example.ecoimpact.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoimpact.ui.theme.EcoImpactTheme

@Composable
fun CarbonScreen(
    modifier: Modifier = Modifier,
    emission: Double = 12.5,
    comparison: List<Pair<String, Double>> = listOf(
        "Carro médio" to 20.0,
        "Ônibus" to 15.0
    ),
    tips: List<String> = listOf(
        "Use transporte público",
        "Prefira caminhada ou bicicleta"
    )
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Valor principal
        Text(
            text = "${emission} kg",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE53935) // vermelho
        )
        Text(
            text = "CO₂ emitido na sua viagem",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(24.dp))

        // Comparação
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)), // azul
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "💡 Comparação",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(Modifier.height(12.dp))
                comparison.forEach { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.first, color = Color.White)
                        Text("${item.second} kg CO₂", color = Color.White)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Dicas
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "🌱 Dicas para reduzir",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(12.dp))
                tips.forEach { dica ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)), // verde claro
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = dica,
                            modifier = Modifier.padding(8.dp),
                            fontSize = 14.sp,
                            color = Color(0xFF2E7D32) // verde escuro
                        )
                    }
                }
            }
        }
    }
}

// --------- Preview ---------
@Preview(showBackground = true)
@Composable
fun CarbonScreenPreview() {
    EcoImpactTheme {
        CarbonScreen()
    }
}
