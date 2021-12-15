package com.hieuwu.groceriesstore.presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hieuwu.groceriesstore.MainActivity
import com.hieuwu.groceriesstore.R
import timber.log.Timber

class FirebaseCloudMessageService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.d(remoteMessage.toString())
        remoteMessage.notification?.let {
            showNotification(it.title!!, it.body!!)
        }

    }

    private fun showNotification(
        title: String,
        message: String
    ) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val channelId = "notification_channel";
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        );

        val builder = NotificationCompat
            .Builder(
                applicationContext,
                channelId
            )
            .setSmallIcon(R.drawable.ic_carot_icon)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setContentTitle(title)
            .setContentText(message)

        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channelId, "groceries_store",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }

        notificationManager.notify(0, builder.build());
    }
}