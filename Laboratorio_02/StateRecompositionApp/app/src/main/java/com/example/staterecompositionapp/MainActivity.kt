package com.example.staterecompositionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.staterecompositionapp.ui.theme.StateRecompositionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateRecompositionAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {

                        Text(text = "Lista Simple", style = MaterialTheme.typography.titleMedium)
                        ListaSimple()

                        Text(text = "Lista Dinámica", style = MaterialTheme.typography.titleMedium)
                        ListaDinamica()
                    }
                }
            }
        }
    }
}

@Composable
fun ListaSimple() {
    val lista = listOf("A", "B", "C")

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(120.dp)
    ) {
        items(lista) { item ->
            Text(text = item)
        }
    }
}

@Composable
fun ListaDinamica() {
    var lista by remember { mutableStateOf(listOf<String>()) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Button(onClick = {
            lista = lista + "Item ${lista.size + 1}"
        }) {
            Text(text = "Agregar")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(150.dp)
        ) {
            items(lista) { item ->
                Text(text = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    StateRecompositionAppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            ListaSimple()
            ListaDinamica()
        }
    }
}

@Composable
fun InputTexto() {
    var texto by remember { mutableStateOf("")}

    Column {
        TextField(
            value = texto,
            onValueChange = { texto = it }
        )

        Text("Escribiste: $texto")
    }
}


@Composable
fun Contador(modifier: Modifier) {
    var contador by remember {mutableStateOf(0)}

    Column(modifier = modifier) {
        Text("Valor: $contador")
        Button(onClick = { contador++}) {
            Text("Incrementar")
        }
    }

}

/*
@Preview(showBackground = true)
@Composable
fun ContadorPreview() {
    StateRecompositionAppTheme {
        Contador()
    }
}
*/