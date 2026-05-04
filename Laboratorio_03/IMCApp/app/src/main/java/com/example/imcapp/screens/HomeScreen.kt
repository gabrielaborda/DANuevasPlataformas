package com.example.imcapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.imcapp.utils.calcularIMC

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {

    var isMale by remember { mutableStateOf(true) }
    var altura by remember { mutableStateOf(175f) }
    var peso by remember { mutableStateOf(70) }
    var edad by remember { mutableStateOf(25) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Título
        Text(
            text = "Índice Masa Corporal",
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(
            text = "Ingrese y seleccione la siguiente información",
            style = MaterialTheme.typography.bodySmall
        )

        // Género
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isMale) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant
                ),
                onClick = { isMale = true }
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Hombre")
                }
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (!isMale) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant
                ),
                onClick = { isMale = false }
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Mujer")
                }
            }
        }

        // Altura
        Card {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Altura")

                Text(
                    text = "${altura.toInt()} cm",
                    fontSize = 28.sp
                )

                Slider(
                    value = altura,
                    onValueChange = { altura = it },
                    valueRange = 100f..220f
                )
            }
        }

        // Peso y Edad
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            // Peso
            Card(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Peso (kg)")
                    Text("$peso", fontSize = 24.sp)

                    Row {
                        Button(onClick = { peso-- }) {
                            Text("-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { peso++ }) {
                            Text("+")
                        }
                    }
                }
            }

            // Edad
            Card(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Edad")
                    Text("$edad", fontSize = 24.sp)

                    Row {
                        Button(onClick = { edad-- }) {
                            Text("-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { edad++ }) {
                            Text("+")
                        }
                    }
                }
            }
        }

        // Botón
        Button(
            onClick = {
                val imc = calcularIMC(peso.toFloat(), altura)
                navController.navigate("result/$imc")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("CALCULAR")
        }
    }
}