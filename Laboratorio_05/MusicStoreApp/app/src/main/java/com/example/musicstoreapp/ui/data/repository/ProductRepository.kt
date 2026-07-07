package com.example.musicstoreapp.ui.data.repository

import com.example.musicstoreapp.ui.data.dao.ProductDao
import com.example.musicstoreapp.ui.data.model.ProductEntity
import com.example.musicstoreapp.ui.data.remote.ApiClient
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    // Retorna el Flow de la base de datos local para observar cambios en tiempo real
    fun getProducts(): Flow<List<ProductEntity>> = productDao.getProducts()

    fun getProductById(id: Int): Flow<ProductEntity?> = productDao.getProductById(id)

    suspend fun syncProducts() {
        try {
            // Se usa ApiClient (el objeto correcto definido en tu proyecto)
            val remoteProducts = ApiClient.api.getProducts()
            
            val entities = remoteProducts.map { product ->
                ProductEntity(
                    id = product.id,
                    title = product.title,
                    price = product.price,
                    description = product.description,
                    image = product.image,
                    category = product.category
                )
            }

            productDao.deleteAll()
            productDao.insertProducts(entities)
        } catch (e: Exception) {
            // Si el backend (http://127.0.0.1:8000) no está disponible,
            // la app seguirá funcionando con los datos de la base de datos local.
            e.printStackTrace()
        }
    }
}
