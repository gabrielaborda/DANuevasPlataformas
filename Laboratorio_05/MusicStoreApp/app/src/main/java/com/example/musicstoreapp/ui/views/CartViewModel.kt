package com.example.musicstoreapp.ui.views

import androidx.lifecycle.ViewModel
import com.example.musicstoreapp.ui.model.Product
import com.example.musicstoreapp.ui.data.repository.CartRepository

class CartViewModel: ViewModel() {

    val cartItems: List<Product> get() = CartRepository.items
    val total: Double get() = CartRepository.getTotal()

    fun addProduct(product: Product) = CartRepository.addProduct(product)
    fun removeProduct(product: Product) = CartRepository.removeProduct(product)
}