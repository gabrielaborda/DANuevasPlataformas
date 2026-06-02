package com.example.musicstoreapp.ui.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.musicstoreapp.ui.model.Product

object CartRepository {
    private val _items = mutableStateListOf<Product>()
    val items: List<Product> get() = _items

    fun addProduct(product: Product){
        _items.add(product)
    }

    fun removeProduct(product: Product){
        _items.remove(product)
    }

    fun getTotal(): Double{
        return _items.sumOf { it.precio }
    }
}
