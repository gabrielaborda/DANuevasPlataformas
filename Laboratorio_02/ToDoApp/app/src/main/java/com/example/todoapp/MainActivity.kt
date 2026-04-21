package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToDoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ToDoScreen(modifier: Modifier = Modifier) {

    var texto by remember { mutableStateOf("") }
    var lista by remember { mutableStateOf(listOf<String>()) }
    var editIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Título
        Text(
            text = "ToDo App",
            style = MaterialTheme.typography.titleLarge
        )

        // Input + botón
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            TextField(
                value = texto,
                onValueChange = { texto = it },
                modifier = Modifier.weight(1f),
                label = { Text("Nueva tarea") }
            )

            Button(onClick = {
                if (texto.isNotBlank()) {
                    if (editIndex != null) {
                        // Editar
                        lista = lista.toMutableList().also {
                            it[editIndex!!] = texto
                        }
                        editIndex = null
                    } else {
                        // Agregar
                        lista = lista + texto
                    }
                    texto = ""
                }
            }) {
                Text(if (editIndex != null) "Actualizar" else "Agregar")
            }
        }

        // Lista
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(lista) { index, tarea ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = tarea,
                            modifier = Modifier.weight(1f)
                        )

                        Row {
                            // Editar
                            TextButton(onClick = {
                                texto = tarea
                                editIndex = index
                            }) {
                                Text("Editar")
                            }

                            // Eliminar
                            TextButton(onClick = {
                                lista = lista.toMutableList().also {
                                    it.removeAt(index)
                                }
                            }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewToDo() {
    ToDoAppTheme {
        ToDoScreen()
    }
}