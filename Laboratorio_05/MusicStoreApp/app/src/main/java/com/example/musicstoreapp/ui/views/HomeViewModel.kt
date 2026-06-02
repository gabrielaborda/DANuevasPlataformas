package com.example.musicstoreapp.ui.views

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.musicstoreapp.ui.model.Product
import com.example.musicstoreapp.ui.data.repository.ProductRepository

class HomeViewModel(
    private val repository: ProductRepository
): ViewModel() {

    var searchText by mutableStateOf("")
    val products: List<Product>
        get() = repository.getProducts().filter{
            it.nombre.contains(searchText, ignoreCase = true)
        }
}