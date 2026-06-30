package com.example.musicstoreapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.musicstoreapp.ui.data.model.Product

@Composable
fun ProductItem(product: Product) {
    Card {
        Column {
            Text(product.title)
            Text("$${product.price}")
        }
    }
}