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
        val title = message.notification?.title ?: "Nueva Notificación"
        val body = message.notification?.body ?: ""
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val builder = NotificationCompat.Builder(this, NotificationManager.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
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