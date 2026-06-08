package com.example.musicstoreapp.ui.state

import com.example.musicstoreapp.ui.data.model.Product

data class CartUiState(
    val items: List<Product> = emptyList(),
    val total: Double = 0.0
)