package com.example.recompositionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recompositionapp.ui.components.PrimaryButton
import com.example.recompositionapp.ui.components.ProductList
import com.example.recompositionapp.ui.components.SearchBar
import com.example.recompositionapp.ui.theme.RecompositionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecompositionAppTheme {
                val productos = listOf(
                    "Laptop",
                    "Mouse",
                    "Teclado",
                    "Monitor"
                )

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column( modifier = Modifier.padding(innerPadding)) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SearchBar(
                                value = "Buscar productos",
                                onValueChange = { },
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            PrimaryButton(
                                text = "Buscar",
                                modifier = Modifier.wrapContentWidth()
                            ) {
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        ProductList(
                            productos = productos
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecompositionAppTheme {
        Greeting("Android")
    }
}