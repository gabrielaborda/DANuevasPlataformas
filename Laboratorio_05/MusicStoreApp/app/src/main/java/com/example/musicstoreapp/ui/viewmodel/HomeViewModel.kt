package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // 1. Necesitamos esto para las corrutinas
import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.state.HomeUiState
import kotlinx.coroutines.launch // 2. Necesitamos el constructor launch

class HomeViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    // Inicializamos el estado con una lista vacía mientras los datos cargan de internet
    var uiState by mutableStateOf(HomeUiState(products = emptyList()))
        private set

    // Guardamos una copia local de la lista completa para filtrar en memoria
    private var allProducts: List<Product> = emptyList()

    init {
        // Al crear el ViewModel, disparamos la carga de datos de internet
        fetchProducts()
    }

    private fun fetchProducts() {
        // 'viewModelScope.launch' abre la puerta al mundo de las funciones suspendidas
        viewModelScope.launch {
            try {
                val productsFromNetwork = repository.getProducts()
                allProducts = productsFromNetwork // Guardamos el respaldo para el buscador

                uiState = uiState.copy(
                    products = productsFromNetwork
                )
            } catch (e: Exception) {
                // Aquí deberías manejar errores (ej. sin internet, servidor caído)
                e.printStackTrace()
            }
        }
    }

    fun updateSearch(text: String) {
        // ¡Ojo aquí! Filtramos sobre 'allProducts' (en memoria), NO desde el repository.
        // Así la búsqueda es instantánea y no gasta internet por cada letra escrita.
        val filteredProducts = allProducts.filter {
            it.title.contains(text, ignoreCase = true)
        }

        uiState = uiState.copy(
            searchText = text,
            products = filteredProducts
        )
    }
}