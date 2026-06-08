package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.state.HomeUiState

class HomeViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    var uiState by mutableStateOf(
        HomeUiState(
            products = repository.getProducts()
        )
    )
        private set

    fun updateSearch(text: String) {

        val filteredProducts = repository.getProducts().filter {
            it.nombre.contains(text, ignoreCase = true)
        }

        uiState = uiState.copy(
            searchText = text,
            products = filteredProducts
        )
    }
}