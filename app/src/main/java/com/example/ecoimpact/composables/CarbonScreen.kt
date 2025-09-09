package com.example.ecoimpact.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecoimpact.ui.theme.EcoImpactTheme
import com.example.ecoimpact.viewmodel.CarbonViewModel

@Composable
fun CarbonScreen(
    viewModel: CarbonViewModel,
    modifier: Modifier = Modifier,
    tips: List<String> = listOf(
        "Use transporte público",
        "Prefira caminhada ou bicicleta"
    ),
    onNext: () -> Unit = {}
) {
    // ViewModel
    val emissionResult by viewModel.emissionResult.collectAsState() // ou collectAsStateWithLifecycle()

    // Emissão real ou fallback 0.0
    val emission = emissionResult?.data?.attributes?.carbonKg ?: 0.0

    // Comparação dinâmica com base na emissão real
    val comparison = listOf(
        "Carro médio" to (emission * 1.2),   // exemplo: 20% a mais que sua emissão
        "Ônibus" to (emission * 0.8)        // exemplo: 20% a menos que sua emissão
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Valor principal
        Text(
            text = "${String.format("%.2f", emission)} kg",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE53935)
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
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)),
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
                        Text("${String.format("%.2f", item.second)} kg CO₂", color = Color.White)
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = dica,
                            modifier = Modifier.padding(8.dp),
                            fontSize = 14.sp,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botão próximo
        Button(
            onClick = { onNext() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),

        ) {
            Text("Próximo")
        }
    }
}


// --------- Preview ---------
@Preview(showBackground = true)
@Composable
fun CarbonScreenPreview() {
    EcoImpactTheme {
        CarbonScreen(viewModel = viewModel())
    }
}
