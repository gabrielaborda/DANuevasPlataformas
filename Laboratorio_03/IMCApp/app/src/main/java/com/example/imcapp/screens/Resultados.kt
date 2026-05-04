package com.example.imcapp.screens.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultadosScreen(
    imc: Float,
    onVolver: () -> Unit
) {

    val categoria = when {
        imc < 18.5 -> "Bajo peso"
        imc < 25 -> "Normal"
        imc < 30 -> "Sobrepeso"
        else -> "Obesidad"
    }

    val mensaje = when (categoria) {
        "Bajo peso" -> "Deberías mejorar tu alimentación"
        "Normal" -> "Estás en un peso saludable"
        "Sobrepeso" -> "Podrías hacer más ejercicio"
        else -> "Consulta con un especialista"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Título
            Text(
                text = "Tu Resultado",
                style = MaterialTheme.typography.headlineMedium
            )

            // Resultado IMC
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("IMC", style = MaterialTheme.typography.bodyMedium)

                    Text(
                        text = String.format("%.2f", imc),
                        fontSize = 40.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = categoria,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = mensaje,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Botón volver
        Button(
            onClick = onVolver,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("VOLVER")
        }
    }
}