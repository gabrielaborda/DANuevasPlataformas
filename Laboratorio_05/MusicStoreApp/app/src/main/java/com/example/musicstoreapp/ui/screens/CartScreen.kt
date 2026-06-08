package com.example.musicstoreapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.musicstoreapp.ui.components.AppBottomBar
import com.example.musicstoreapp.ui.components.AppTopBar
import com.example.musicstoreapp.ui.components.CartItem
import com.example.musicstoreapp.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel()
) {
    var cartItems = viewModel.uiState.items
    val total = viewModel.uiState.total

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Carrito",
                onCartClick = { }
            )
        },

        bottomBar = {
            AppBottomBar(
                currentRoute = "cart",
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
                        onRemove = {viewModel.removeProduct(product)
                        }
                    )
                }
            }
            Text(
                text = "Total: $ ${"%.2f".format(total)}",
            )

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar compra")
            }
        }
    }
}

