package com.example.musicstoreapp.ui.state

import com.example.musicstoreapp.ui.data.model.Product

data class HomeUiState(
    val searchText: String = "",
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)