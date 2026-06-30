package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // 1. Importamos el scope para corrutinas
import com.example.musicstoreapp.ui.data.analytics.ProductVisitLogger
import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.repository.CartRepository
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.state.DetailUiState
import kotlinx.coroutines.launch // 2. Importamos el constructor launch

class DetailViewModel(
    private val repository: ProductRepository,
    private val visitLogger: ProductVisitLogger
) : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    fun loadProduct(productId: Int) {
        // Abrimos la corrutina para poder llamar a getProducts()
        viewModelScope.launch {
            try {
                // Buscamos el producto en la lista que viene de internet
                val product = repository
                    .getProducts()
                    .find { it.id == productId }

                // Si lo encuentra, registra la visita en la analítica
                product?.let {
                    visitLogger.logVisit(it)
                }

                // Actualizamos la pantalla con el producto encontrado
                uiState = uiState.copy(
                    product = product
                )
            } catch (e: Exception) {
                // Manejo de errores en caso de caída de red
                e.printStackTrace()
            }
        }
    }

    fun addProduct(product: Product) {
        CartRepository.addProduct(product)
    }
}