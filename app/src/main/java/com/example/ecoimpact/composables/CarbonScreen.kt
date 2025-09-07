package com.example.ecoimpact.composables


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoimpact.ui.theme.EcoImpactTheme
import androidx.compose.ui.platform.LocalContext


@Composable
fun CarbonScreen(
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Valor principal
        Text(
            text = "\uD83D\uDE97 Transporte",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black // vermelho
        )
        Text(
            text = "Calcule sua pegada de carbono",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(24.dp))

        //Campo para a Distância
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
                    // Botões

                    //Carro
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clickable {
                                selectedTransport = "Carro"
                            }, // Adiciona o clique e atualiza o estado
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedTransport == "Carro") Color.LightGray else Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp // Sombreado padrão
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "🚗", fontSize = 24.sp)
                            Text(text = "Carro", fontSize = 12.sp)
                        }

                    }

                    //Ônibus
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clickable {
                                selectedTransport = "Ônibus"
                            }, // Adiciona o clique e atualiza o estado
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedTransport == "Ônibus") Color.LightGray else Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp // Sombreado padrão
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "\uD83D\uDE8C", fontSize = 24.sp)
                            Text(text = "Ônibus", fontSize = 12.sp)
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    //Metrô
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clickable {
                                selectedTransport = "Metrô"
                            }, // Adiciona o clique e atualiza o estado
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedTransport == "Metrô") Color.LightGray else Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp // Sombreado padrão
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "\uD83D\uDE8A", fontSize = 24.sp)
                            Text(text = "Metrô", fontSize = 12.sp)
                        }
                    }
                    //Bicicleta
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clickable {
                                selectedTransport = "Bicicleta"
                            }, // Adiciona o clique e atualiza o estado
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedTransport == "Bicicleta") Color.LightGray else Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp // Sombreado padrão
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "\uD83D\uDEB2", fontSize = 24.sp)
                            Text(text = "Bicicleta", fontSize = 12.sp)
                        }
                    }
                }

                }
            Spacer(Modifier.height(24.dp)) // Espaço entre os botões de transporte e o novo botão

            Button(
                onClick = {
                    if (distancia.isEmpty()) {
                        distanciaHasError = true
                        Toast.makeText(context, "Preencha a distância", Toast.LENGTH_SHORT).show()
                    } else {
                        distanciaHasError = false
                        // Passa valores para a tela de resultado
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

private fun ColumnScope.onNavigateToResult() {
    TODO("Not yet implemented")
}

// --------- Preview ---------
@Preview(showBackground = true)
@Composable
fun CarbonScreenPreview() {
    EcoImpactTheme {
        CarbonScreen(
            onNavigateToResult = { _, _ ->},
            onLogin = {}
        )
    }
}
