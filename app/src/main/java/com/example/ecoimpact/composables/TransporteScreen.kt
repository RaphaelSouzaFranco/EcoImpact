package com.example.ecoimpact.composables

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecoimpact.ui.theme.EcoImpactTheme
import com.example.ecoimpact.viewmodel.CarbonViewModel

@Composable
fun TransporteScreen(
    onNavigateToResult: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    emission: Double = 12.5,
    comparison: List<Pair<String, Double>> = listOf(
        "Carro médio" to 20.0,
        "Ônibus" to 15.0
    ),
    tips: List<String> = listOf(
        "Use transporte público",
        "Prefira caminhada ou bicicleta"
    ),
    onLogin: () -> Unit
) {
    val context = LocalContext.current
    var distancia by remember { mutableStateOf("15") }
    var distanciaHasError by remember { mutableStateOf(false) }
    var selectedTransport by remember { mutableStateOf("Carro") }

    // ViewModel
    val carbonViewModel: CarbonViewModel = viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\uD83D\uDE97 Transporte",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Calcule sua pegada de carbono",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(24.dp))

        // Campo para a Distância
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(contentColor = Color.Black),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Distância (Km)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = distancia,
                    onValueChange = { distancia = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Distância") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = distanciaHasError
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Botões de transporte
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Meio de transporte",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Carro" to "🚗", "Ônibus" to "\uD83D\uDE8C").forEach { (name, emoji) ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp)
                                .clickable { selectedTransport = name },
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedTransport == name) Color.LightGray else Color.White
                            ),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = emoji, fontSize = 24.sp)
                                Text(text = name, fontSize = 12.sp)
                            }
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Metrô" to "\uD83D\uDE8A", "Bicicleta" to "\uD83D\uDEB2").forEach { (name, emoji) ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp)
                                .clickable { selectedTransport = name },
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedTransport == name) Color.LightGray else Color.White
                            ),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = emoji, fontSize = 24.sp)
                                Text(text = name, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Botão Calcular Impacto
            Button(
                onClick = {
                    if (distancia.isEmpty()) {
                        distanciaHasError = true
                        Toast.makeText(context, "Preencha a distância", Toast.LENGTH_SHORT).show()
                    } else {
                        distanciaHasError = false

                        // Chama o ViewModel para calcular a emissão real
                        carbonViewModel.calculateEmission(distancia, selectedTransport)

                        // Navega para a tela de resultado
                        onNavigateToResult(distancia, selectedTransport)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    text = "Calcular Impacto",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// --------- Preview ---------
@Preview(showBackground = true)
@Composable
fun TransporteScreenPreview() {
    EcoImpactTheme {
        TransporteScreen(
            onNavigateToResult = { _, _ ->},
            onLogin = {}
        )
    }
}
