package com.example.musicstoreapp.ui.services

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.musicstoreapp.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_TOKEN", token)
    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
// Log para depuración
        Log.d("FCM_MSG", "Message received: ${message.notification?.body}")
// Mostrar notificación manual si la app está en primer plano o si es solo data
        showNotification(message)
    }
    private fun showNotification(message: RemoteMessage) {
        val data = message.data
        val type = data["type"] ?: "DEFAULT"
        
        val title = message.notification?.title ?: "Music Store"
        val body = message.notification?.body ?: ""

        // 1. Personalización de Icono y Prioridad según el contexto
        val (icon, priority) = when (type) {
            NotificationManager.TYPE_NEW_PRODUCT -> 
                android.R.drawable.ic_input_add to NotificationCompat.PRIORITY_DEFAULT
            NotificationManager.TYPE_OFFER -> 
                android.R.drawable.btn_star_big_on to NotificationCompat.PRIORITY_HIGH
            NotificationManager.TYPE_ORDER_UPDATE -> 
                android.R.drawable.ic_popup_sync to NotificationCompat.PRIORITY_HIGH
            else -> 
                android.R.drawable.ic_dialog_info to NotificationCompat.PRIORITY_DEFAULT
        }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            // 2. Pasar el ID del producto o pedido para que la App sepa qué abrir
            if (data.containsKey("id")) {
                putExtra("target_id", data["id"])
                putExtra("type", type)
            }
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, NotificationManager.CHANNEL_ID)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        try {
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
        } catch (e: SecurityException) {
            Log.e("FCM_ERROR", "No permission to post notification", e)
        }
    }
}