package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.state.HomeUiState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    // Guardamos una copia local de la lista completa para filtrar en memoria
    private var allProducts: List<Product> = emptyList()

    init {
        // 1. Empezamos a observar la base de datos local (Offline First)
        observeProducts()
        // 2. Disparamos la sincronización con el servidor
        refreshProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            // Recolectamos el Flow que viene de Room (ProductEntity)
            repository.getProducts().collect { entities ->
                // Convertimos de ProductEntity (DB) a Product (UI/Domain)
                val domainProducts = entities.map { entity ->
                    Product(
                        id = entity.id,
                        title = entity.title,
                        price = entity.price,
                        description = entity.description,
                        image = entity.image,
                        category = entity.category
                    )
                }
                
                allProducts = domainProducts
                
                // Si no hay búsqueda activa, mostramos todo
                if (uiState.searchText.isEmpty()) {
                    uiState = uiState.copy(products = domainProducts)
                } else {
                    // Si hay búsqueda, volvemos a filtrar con los nuevos datos
                    updateSearch(uiState.searchText)
                }
            }
        }
    }

    private fun refreshProducts() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            try {
                // Sincronizamos la API con la base de datos local
                repository.syncProducts()
            } catch (e: Exception) {
                // Si falla la red, no importa, ya estamos observando la DB local
                e.printStackTrace()
            } finally {
                uiState = uiState.copy(isLoading = false)
            }
        }
    }

    fun updateSearch(text: String) {
        val filteredProducts = allProducts.filter {
            it.title.contains(text, ignoreCase = true)
        }

        uiState = uiState.copy(
            searchText = text,
            products = filteredProducts
        )
    }
}
