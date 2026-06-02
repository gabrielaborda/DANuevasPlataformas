package com.example.musicstoreapp.ui.views

import androidx.lifecycle.ViewModel
import com.example.musicstoreapp.ui.model.Product
import com.example.musicstoreapp.ui.data.repository.CartRepository
import com.example.musicstoreapp.ui.data.repository.ProductRepository

class DetailViewModel(
    private val productRepository: ProductRepository = ProductRepository()
): ViewModel() {
    val products: List<Product>
        get() = productRepository.getProducts()

    fun addProduct(product: Product) = CartRepository.addProduct(product)

}