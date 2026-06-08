package com.example.musicstoreapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.musicstoreapp.ui.data.model.Product

@Composable
fun ProductList(
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(products, key = { it.id }) { product ->

            ProductCard(
                product = product,
                onClick = {
                    onProductClick(product)
                }
            )
        }
    }
}