package com.example.musicstoreapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicstoreapp.ui.data.model.Product
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    var products = mutableStateOf<List<Product>>(emptyList())
        private set
    var isLoading = mutableStateOf(false)
    fun loadProducts() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                products.value = repository.getProducts()
            } catch (e: Exception) {
                products.value = emptyList()
            } finally {
                isLoading.value = false
            }
        }
    }
}