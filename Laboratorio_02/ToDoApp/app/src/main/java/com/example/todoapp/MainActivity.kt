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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Título
        Text(
            text = "📝 ToDo App",
            style = MaterialTheme.typography.headlineMedium
        )

        // Input con estilo
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp),
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
                            if (editIndex != null) {
                                lista = lista.toMutableList().also {
                                    it[editIndex!!] = texto
                                }
                                editIndex = null
                            } else {
                                lista = lista + texto
                            }
                            texto = ""
                        }
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(if (editIndex != null) "Actualizar" else "Agregar")
                }
            }
        }

        // Lista estilizada
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(lista) { index, tarea ->

                Card(
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = tarea,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Row {

                            TextButton(onClick = {
                                texto = tarea
                                editIndex = index
                            }) {
                                Text("✏️")
                            }

                            TextButton(onClick = {
                                lista = lista.toMutableList().also {
                                    it.removeAt(index)
                                }
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

@Preview(showBackground = true)
@Composable
fun PreviewToDo() {
    ToDoAppTheme {
        ToDoScreen()
    }
}