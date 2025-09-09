package com.example.ecoimpact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF6EC)) // Fundo claro/bege
                    )
     {
         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(32.dp),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center
        ) {

             // Logo
             Box(
                 modifier = Modifier
                     .size(100.dp)
                     .clip(CircleShape)
                     .background(Color(0xFF4CAF50)), // Verde principal
                 contentAlignment = Alignment.Center
             ) {
                 Text(
                     text = "🍃", // Ícone folha (pode ser trocado por imagem futuramente)
                     fontSize = 48.sp
                 )
             }

            Spacer(modifier = Modifier.height(32.dp))

            // Título
            Text(
                text = "EcoImpact",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )

             Spacer(modifier = Modifier.height(12.dp))

            // Descrição
            Text(
                text = "Descubra o impacto das suas ações no meio ambiente\n e aprenda a reduzir sua pegada de carbono",
                fontSize = 16.sp,
                color = Color(0xFF555555),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

             Spacer(modifier = Modifier.height(40.dp))

            //  Email
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    showError = false
                },
                label = { Text("Email", color = Color(0xFF4CAF50)) },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email", tint = Color(0xFF4CAF50))
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                isError = showError && email.isBlank(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF2E7D32),
                    unfocusedTextColor = Color(0xFF2E7D32),
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color(0xFF4CAF50).copy(alpha = 0.5f),
                    errorBorderColor = Color(0xFFE57373),
                    cursorColor = Color(0xFF4CAF50)
                ),
                shape = RoundedCornerShape(25.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //  Senha
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    showError = false
                },
                label = { Text("Senha", color = Color(0xFF4CAF50)) },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Senha", tint = Color(0xFF4CAF50))
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                isError = showError && password.isBlank(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF2E7D32),
                    unfocusedTextColor = Color(0xFF2E7D32),
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color(0xFF4CAF50).copy(alpha = 0.5f),
                    errorBorderColor = Color(0xFFE57373),
                    cursorColor = Color(0xFF4CAF50)
                ),
                shape = RoundedCornerShape(25.dp)
            )

            if (showError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = Color(0xFFFFCDD2),
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botao de Login
            Button(
                onClick = {
                    when {
                        email.isBlank() && password.isBlank() -> {
                            showError = true
                            errorMessage = "Por favor, preencha email e senha"
                        }
                        email.isBlank() -> {
                            showError = true
                            errorMessage = "Por favor, preencha o email"
                        }
                        password.isBlank() -> {
                            showError = true
                            errorMessage = "Por favor, preencha a senha"
                        }
                        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                            showError = true
                            errorMessage = "Por favor, insira um email válido"
                        }
                        else -> {
                            showError = false
                            onLoginSuccess()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Entrar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
