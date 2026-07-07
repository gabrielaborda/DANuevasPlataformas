package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicstoreapp.ui.data.analytics.ProductVisitLogger
import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.repository.CartRepository
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.state.DetailUiState
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ProductRepository,
    private val visitLogger: ProductVisitLogger
) : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            // Observamos el producto específico desde la base de datos (Fuente de verdad)
            repository.getProductById(productId).collect { entity ->
                // Mapeamos de ProductEntity (DB) a Product (UI)
                val product = entity?.let {
                    Product(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        description = it.description,
                        image = it.image,
                        category = it.category
                    )
                }

                // Registramos la visita si el producto existe
                product?.let {
                    visitLogger.logVisit(it)
                }

                // Actualizamos el estado para que la pantalla se redibuje
                uiState = uiState.copy(
                    product = product
                )
            }
        }
    }

    fun addProduct(product: Product) {
        CartRepository.addProduct(product)
    }
}
