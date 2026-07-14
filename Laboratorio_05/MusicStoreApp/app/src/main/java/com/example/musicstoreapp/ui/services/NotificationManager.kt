package com.example.musicstoreapp.ui.services

import android.app.NotificationChannel
import android.app.NotificationManager as SystemNotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

class NotificationManager(private val context: Context) {
    companion object {
        const val CHANNEL_ID = "music_store_alerts"
        const val CHANNEL_NAME = "Music Store Alerts"
        
        // Tipos de Notificación
        const val TYPE_NEW_PRODUCT = "NEW_PRODUCT"
        const val TYPE_OFFER = "OFFER"
        const val TYPE_ORDER_UPDATE = "ORDER_UPDATE"
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = SystemNotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = "Notificaciones de productos, ofertas y pedidos"
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as SystemNotificationManager
            notificationManager.createNotificationChannel(channel)
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
