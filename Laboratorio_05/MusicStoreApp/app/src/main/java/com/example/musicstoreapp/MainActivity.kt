package com.example.musicstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
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
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.musicstoreapp.ui.services.NotificationManager

class MainActivity : ComponentActivity() {

    // ==========================================
    // Launcher para solicitar permiso de notificaciones
    // ==========================================
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permiso concedido
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ==========================================
        // Inicializar NotificationManager
        // ==========================================
        val notificationManager = NotificationManager(this)
        notificationManager.createNotificationChannel()
        askNotificationPermission()
        notificationManager.getToken()

        // ==========================================
        // Base de datos
        // ==========================================
        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = ProductRepository(database.productDao())

        // ==========================================
        // Factory del ViewModel
        // ==========================================
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return ProductViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

        // ==========================================
        // Capturar datos de la notificación
        // ==========================================
        val targetId = intent.getStringExtra("target_id")
        val type = intent.getStringExtra("type")

        enableEdgeToEdge()

        setContent {
            MusicStoreAppTheme {
                val viewModel: ProductViewModel =
                    viewModel(factory = viewModelFactory)

                // Pasamos los datos al Navigation para decidir a dónde ir
                AppNavigation(
                    notificationType = type,
                    notificationId = targetId
                )
            }
        }
    }

    // ==========================================
    // Solicitar permiso (Android 13+)
    // ==========================================
    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }
}