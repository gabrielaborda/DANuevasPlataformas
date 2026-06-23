package com.example.musicstoreapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.musicstoreapp.ui.AppViewModelProvider
import com.example.musicstoreapp.ui.navigation.Routes
import com.example.musicstoreapp.ui.viewmodel.DetailViewModel

@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavController,
    viewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    val product = viewModel.uiState.product

    if (product == null) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text("Producto no encontrado")
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Imagen
        Image(
            painter = painterResource(id = product.imagen),
            contentDescription = product.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(MaterialTheme.shapes.large),
            contentScale = ContentScale.Crop
        )

        // Nombre
        Text(
            text = product.nombre,
            style = MaterialTheme.typography.headlineMedium
        )

        // Precio
        Text(
            text = "$ ${product.precio}",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        // Descripción
        Text(
            text = product.descripcion,
            style = MaterialTheme.typography.bodyLarge
        )

        // Botón agregar carrito
        Button(
            onClick = {
                viewModel.addProduct(product)
                navController.navigate(Routes.CART)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar al carrito")
        }

        // Botón volver
        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}