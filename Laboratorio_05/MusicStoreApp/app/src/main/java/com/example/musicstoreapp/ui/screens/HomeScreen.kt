package com.example.musicstoreapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.musicstoreapp.ui.components.AppBottomBar
import com.example.musicstoreapp.ui.components.AppTopBar
import com.example.musicstoreapp.ui.components.ProductList
import com.example.musicstoreapp.ui.components.SearchBar
import com.example.musicstoreapp.ui.data.fakeProducts
import com.example.musicstoreapp.ui.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavController
) {

    var searchText by remember { mutableStateOf("") }

    // Filtrado dinámico
    val filteredProducts = fakeProducts.filter {
        it.nombre.contains(searchText, ignoreCase = true)
    }

    Scaffold(

        topBar = {
            AppTopBar(
                title = "Music Store",
                onCartClick = {
                    navController.navigate(Routes.CART)
                }
            )
        },

        bottomBar = {
            AppBottomBar(

                onHomeClick = { },

                onCartClick = {
                    navController.navigate(Routes.CART)
                },

                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            SearchBar(
                query = searchText,
                onQueryChange = {
                    searchText = it
                }
            )

            ProductList(
                products = filteredProducts,
                onProductClick = { product ->
                    navController.navigate("detail/${product.id}")
                }
            )
        }
    }
}