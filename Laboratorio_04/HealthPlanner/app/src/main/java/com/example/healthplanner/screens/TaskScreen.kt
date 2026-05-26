package com.example.healthplanner.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthplanner.data.TaskRepository

@Composable
fun TaskScreen(
    imc: Float,
    navController: NavController
) {
    val tareas = remember(imc) {
        when {
            imc < 18.5 -> listOf(
                "Aumentar consumo calórico",
                "Comer 5 veces al día",
                "Entrenamiento de fuerza",
                "Consultar nutricionista"
            )
            imc < 25 -> listOf(
                "Mantener dieta balanceada",
                "Ejercicio 3 veces por semana",
                "Dormir 7-8 horas",
                "Hidratación adecuada"
            )
            imc < 30 -> listOf(
                "Reducir consumo de azúcares",
                "Ejercicio cardiovascular",
                "Caminar 30 minutos diarios",
                "Controlar porciones"
            )
            else -> listOf(
                "Plan nutricional estricto",
                "Ejercicio supervisado",
                "Consulta médica",
                "Reducir sedentarismo"
            )
        }
    }

    // KEY: Set para rastrear cuáles están seleccionadas
    val selectedTasks = remember { mutableStateSetOf<String>() }

    // DerivedStateOf: solo recalcula si selectedTasks cambia
    val canContinue by remember {
        derivedStateOf { selectedTasks.isNotEmpty() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Recomendaciones",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Tu IMC es: %.2f".format(imc),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Selecciona las tareas que deseas agregar:",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(tareas, key = { it }) { tarea ->
                SelectableTaskCard(
                    tarea = tarea,
                    isSelected = tarea in selectedTasks,
                    onToggle = {
                        if (tarea in selectedTasks) selectedTasks.remove(tarea)
                        else selectedTasks.add(tarea)
                    }
                )
            }
        }

        Button(
            onClick = {
                TaskRepository.selectedTasks = selectedTasks.toMutableList()
                navController.navigate("todo")
            },
            enabled = canContinue,      // 👈 derivedStateOf en acción
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (canContinue) "Gestionar tareas (${selectedTasks.size})"
                else "Selecciona al menos una tarea"
            )
        }
    }
}

@Composable
fun SelectableTaskCard(
    tarea: String,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        onClick = onToggle   // toda la card es clickeable
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tarea,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onToggle() }
            )
        }
    }
}