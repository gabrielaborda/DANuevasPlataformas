package com.example.musicstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicstoreapp.ui.data.repository.ProductRepository
import com.example.musicstoreapp.ui.data.database.DatabaseProvider
import com.example.musicstoreapp.ui.navigation.AppNavigation
import com.example.musicstoreapp.ui.theme.MusicStoreAppTheme
import com.example.musicstoreapp.ui.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Inicializamos la base de datos y el repositorio
        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = ProductRepository(database.productDao())

        // 2. Definimos la Factory para el ViewModel
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")

                    return ProductViewModel(repository) as T

                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

        enableEdgeToEdge()

        setContent {

            MusicStoreAppTheme {
                // 3. Obtenemos el ViewModel gestionado por el sistema usando la Factory
                val viewModel: ProductViewModel = viewModel(factory = viewModelFactory)

                Scaffold( modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AppNavigation()
                }
            }
        }
    }
}