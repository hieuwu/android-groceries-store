package com.hieuwu.groceriesstore.presentation.notification

import android.app.NotificationManager
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class FirebaseCloudMessageService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.d(remoteMessage.toString())
        val type = (remoteMessage.data["type"] ?: "")

        remoteMessage.notification?.let {
            val notificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.showNotification(NotificationType.valueOf(type),it.body!!, applicationContext)
        }
    }
}
