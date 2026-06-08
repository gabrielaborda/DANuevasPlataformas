package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.repository.CartRepository
import com.example.musicstoreapp.ui.state.CartUiState
class CartViewModel : ViewModel() {

    var uiState by mutableStateOf(
        CartUiState(
            items = CartRepository.items,
            total = CartRepository.getTotal()
        )
    )
        private set

    fun addProduct(product: Product) {

        CartRepository.addProduct(product)

        refresh()
    }

    fun removeProduct(product: Product) {

        CartRepository.removeProduct(product)

        refresh()
    }

    private fun refresh() {

        uiState = uiState.copy(
            items = CartRepository.items,
            total = CartRepository.getTotal()
        )
    }
}