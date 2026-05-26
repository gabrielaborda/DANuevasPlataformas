package com.example.musicstoreapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicstoreapp.ui.components.AppBottomBar
import com.example.musicstoreapp.ui.components.AppTopBar
import com.example.musicstoreapp.ui.data.fakeProducts
import com.example.musicstoreapp.ui.model.Product

@Composable
fun CartScreen(
    navController: NavController
) {

    //  Carrito temporal
    var cartItems by remember {
        mutableStateOf(fakeProducts.take(2))
    }

    //  Total
    val total = cartItems.sumOf { it.precio }

    Scaffold(

        topBar = {
            AppTopBar(
                title = "Carrito",
                onCartClick = { }
            )
        },

        bottomBar = {
            AppBottomBar(
                onHomeClick = {
                    navController.navigate("home")
                },
                onCartClick = { },
                onProfileClick = {
                    navController.navigate("profile")
                }
            )
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //  Lista productos
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(cartItems, key = { it.id }) { product ->

                    CartItem(
                        product = product,
                        onRemove = {
                            cartItems = cartItems.filter {
                                it.id != product.id
                            }
                        }
                    )
                }
            }

            // 🔹 Total
            Text(
                text = "Total: $ ${"%.2f".format(total)}",
                style = MaterialTheme.typography.headlineSmall
            )

            // 🔹 Checkout
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar compra")
            }
        }
    }
}

@Composable
fun CartItem(
    product: Product,
    onRemove: () -> Unit
) {

    Card {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Image(
                painter = painterResource(id = product.imagen),
                contentDescription = product.nombre,
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = product.nombre,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "$ ${product.precio}"
                )
            }

            TextButton(
                onClick = onRemove
            ) {
                Text("🗑")
            }
        }
    }
}