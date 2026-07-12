package com.example.musicstoreapp.ui.services

import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

class NotificationManager(private val context: Context) {
    companion object {
        const val CHANNEL_ID = "ecommerce_channel"
        const val CHANNEL_NAME = "Ecommerce Notifications"
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Usamos la ruta completa para evitar conflicto con el nombre de esta clase
            val importance = android.app.NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = "Canal para notificaciones de la tienda"
            }
            
            val systemNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) 
                    as android.app.NotificationManager
            
            systemNotificationManager.createNotificationChannel(channel)
        }
    }

    fun getToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FCM_TOKEN", task.result ?: "null")
                } else {
                    Log.w("FCM_ERROR", "Fetching FCM registration token failed", task.exception)
                }
            }
    }
}
