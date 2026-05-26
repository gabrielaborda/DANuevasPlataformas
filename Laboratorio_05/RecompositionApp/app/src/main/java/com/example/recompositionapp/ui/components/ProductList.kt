package com.example.recompositionapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable


@Composable
fun ProductList(
    productos: List<String>
) {
    LazyColumn {
        items (productos) { producto ->
            ProductCard(
                nombre = producto,
                precio = 1000.0
            )
        }
    }
}