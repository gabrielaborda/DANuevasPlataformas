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
import com.example.musicstoreapp.ui.navigation.Routes
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.views.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = HomeViewModel(
        ProductRepository ())
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Music Store",
                onCartClick = {
                    navController.navigate(Routes.CART) }) },
        bottomBar = {
            AppBottomBar(
                currentRoute = "home",
                onHomeClick = {},
                onCartClick = {
                    navController.navigate(Routes.CART) },
                onProfileClick = {
                    navController.navigate(Routes.PROFILE) }) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchBar(
                query = viewModel.searchText,
                onQueryChange = { viewModel.searchText = it }
            )
            ProductList(
                products = viewModel.products,
                onProductClick = {
                    product -> navController.navigate(
                    "detail/${product.id}")
                }
            )
        }
    }
}