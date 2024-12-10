package com.febriandi.agrojaya.data.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.data.Repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Logging informasi lengkap tentang pesan
        Log.d("FCM_MESSAGE", "From: ${remoteMessage.from}")

        // Log data tambahan jika ada
        remoteMessage.data.isNotEmpty().let {
            Log.d("FCM_MESSAGE", "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let { message ->
            val title = message.title ?: "Judul Kosong"
            val body = message.body ?: "Isi Pesan Kosong"

            // Log detail notifikasi
            Log.d("FCM_MESSAGE", "Notification Title: $title")
            Log.d("FCM_MESSAGE", "Notification Body: $body")

            showNotification(title, body)
        }
    }

    private fun showNotification(title: String, body: String) {
        val channelId = "FCM_CHANNEL"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSmallIcon(R.drawable.icon)
            .setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "FCM Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Log sebelum menampilkan notifikasi
        Log.d("FCM_MESSAGE", "Showing notification with title: $title")
        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Log token baru
        Log.d("FCM_TOKEN", "New token generated: $token")

        val currentUser = firebaseAuth.currentUser
        currentUser?.let { user ->
            Log.d("FCM_TOKEN", "Updating token for user: ${user.uid}")
            CoroutineScope(Dispatchers.IO).launch {
                userRepository.updateFCMToken(user.uid, token)
            }
        }
    }
}