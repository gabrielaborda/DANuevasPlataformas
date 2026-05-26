package com.example.healthplanner.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthplanner.data.TaskRepository

data class Task(
    val id: Int,
    val nombre: String,
    val completada: Boolean = false
)

@Composable
fun ToDoScreen(modifier: Modifier = Modifier) {

    var texto by remember { mutableStateOf("") }
    var lista by remember {
        mutableStateOf(
            TaskRepository.selectedTasks.mapIndexed { index, nombre ->
                Task(id = index, nombre = nombre)
            }
        )
    }
    var editTask by remember { mutableStateOf<Task?>(null) }
    var idCounter by remember {
        mutableStateOf(TaskRepository.selectedTasks.size)
    }

    val completadas by remember {
        derivedStateOf { lista.count { it.completada } }
    }

    val pendientes by remember {
        derivedStateOf { lista.size - completadas }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Título
        Text(
            text = "📝 ToDo",
            style = MaterialTheme.typography.headlineMedium
        )

        // 🔹 Estadísticas
        Text("Total: ${lista.size}")
        Text("Completadas: $completadas")
        Text("Pendientes: $pendientes")

        // 🔹 Input
        Card(
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                TextField(
                    value = texto,
                    onValueChange = { texto = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("Nueva tarea") },
                    singleLine = true
                )

                Button(
                    onClick = {
                        if (texto.isNotBlank()) {

                            if (editTask != null) {
                                lista = lista.map {
                                    if (it.id == editTask!!.id)
                                        it.copy(nombre = texto)
                                    else it
                                }
                                editTask = null
                            } else {
                                lista = lista + Task(
                                    id = idCounter++,
                                    nombre = texto
                                )
                            }

                            texto = ""
                        }
                    }
                ) {
                    Text(if (editTask != null) "Actualizar" else "Agregar")
                }
            }
        }

        // 🔹 Lista con keys
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = lista,
                key = { it.id }
            ) { tarea ->

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row {
                            Checkbox(
                                checked = tarea.completada,
                                onCheckedChange = { checked ->
                                    lista = lista.map {
                                        if (it.id == tarea.id)
                                            it.copy(completada = checked)
                                        else it
                                    }
                                }
                            )

                            Text(
                                text = tarea.nombre,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        Row {
                            TextButton(onClick = {
                                texto = tarea.nombre
                                editTask = tarea
                            }) {
                                Text("✏️")
                            }

                            TextButton(onClick = {
                                lista = lista.filter { it.id != tarea.id }
                            }) {
                                Text("🗑️")
                            }
                        }
                    }
                }
            }
        }
    }
}