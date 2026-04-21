package com.example.lab_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_01.components.TarjetaInfo
import com.example.lab_01.ui.theme.Lab_01Theme

// Modelo de datos
data class Tarea(
    val titulo: String,
    val descripcion: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab_01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val listaTareas = listOf(
        Tarea("Estudiar Compose", "Revisar componentes reutilizables"),
        Tarea("Hacer laboratorio", "Completar ejercicio 3"),
        Tarea("Leer documentación", "Aprender LazyColumn"),
        Tarea("Practicar Kotlin", "Funciones y estados")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listaTareas) { tarea ->

            TarjetaInfo(
                titulo = tarea.titulo,
                descripcion = tarea.descripcion,
                textoBoton = "Ver",
                onClick = {
                    println("Click en ${tarea.titulo}")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    Lab_01Theme {
        MainScreen()
    }
}

/**
 * @Composable
 * fun MainScreen(modifier: Modifier = Modifier) {
 *
 *     var mensaje by remember { mutableStateOf("") }
 *
 *     Column(
 *         modifier = modifier
 *             .fillMaxSize()
 *             .padding(16.dp),
 *         verticalArrangement = Arrangement.Center,
 *         horizontalAlignment = Alignment.CenterHorizontally
 *     ) {
 *
 *         // Título
 *         Text(
 *             text = "Bienvenido",
 *             fontSize = 28.sp
 *         )
 *
 *         Spacer(modifier = Modifier.height(8.dp))
 *
 *         // Subtítulo
 *         Text(
 *             text = "Laboratorio 01 - Jetpack Compose",
 *             fontSize = 18.sp
 *         )
 *
 *         Spacer(modifier = Modifier.height(20.dp))
 *
 *         // Botón
 *         Button(onClick = {
 *             mensaje = "¡Botón presionado!"
 *         }) {
 *             Text(text = "Presionar")
 *         }
 *
 *         Spacer(modifier = Modifier.height(20.dp))
 *
 *         // Mensaje que aparece al presionar
 *         Text(
 *             text = mensaje,
 *             fontSize = 16.sp
 *         )
 *     }
 * }
 */


/*
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.padding(16.dp)
    ) {

        TarjetaInfo(
            titulo = "Opción 1",
            descripcion = "Primera tarjeta",
            textoBoton = "Click aquí",
            onClick = { println("Click en tarjeta 1") }
        )

        TarjetaInfo(
            titulo = "Opción 2",
            descripcion = "Segunda tarjeta",
            textoBoton = "Ver más",
            onClick = { println("Click en tarjeta 2") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    Lab_01Theme {
        MainScreen()
    }
}
*/
