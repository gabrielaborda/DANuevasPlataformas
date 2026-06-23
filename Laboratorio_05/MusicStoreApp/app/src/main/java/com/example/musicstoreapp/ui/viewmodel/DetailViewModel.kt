package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.musicstoreapp.ui.data.analytics.ProductVisitLogger
import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.repository.CartRepository
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.state.DetailUiState

class DetailViewModel(
    private val repository: ProductRepository,
    private val visitLogger: ProductVisitLogger
) : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    fun loadProduct(productId: Int) {

        val product = repository
            .getProducts()
            .find { it.id == productId }

        product?.let {
            visitLogger.logVisit(it)
        }

        uiState = uiState.copy(
            product = product
        )
    }

    fun addProduct(product: Product) {

        CartRepository.addProduct(product)
    }
}