package com.hieuwu.groceriesstore.presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.hieuwu.groceriesstore.R

enum class NotificationType {
    ORDER_CREATED,
    DATABASE_SYNCED,
    PROMOTION_SENT,
}

private val NOTIFICATION_ID = 0

private fun createStyleByType(
    type: NotificationType, applicationContext: Context
): NotificationCompat.Style {

    when (type) {
        NotificationType.PROMOTION_SENT -> {
            val bannerImage =
                BitmapFactory.decodeResource(applicationContext.resources, R.drawable.banner)
            return NotificationCompat.BigPictureStyle().bigPicture(bannerImage)
                .bigLargeIcon(null)
        }
        NotificationType.DATABASE_SYNCED -> {
            return NotificationCompat.BigTextStyle().bigText("Some big text")
                .setSummaryText("This is a very log text summary for notification")
        }
        NotificationType.ORDER_CREATED -> {
            return NotificationCompat.InboxStyle().addLine("Line")
                .setBigContentTitle("big conent tittle")
                .setSummaryText("Summary text")
        }
    }
}

private fun getChannelIdByType(type: NotificationType) = when (type) {
    NotificationType.PROMOTION_SENT -> {
        R.string.promotion_sent_notification_channel_id
    }
    NotificationType.DATABASE_SYNCED -> {
        R.string.database_synced_notification_channel_id
    }
    NotificationType.ORDER_CREATED -> {
        R.string.order_created_notification_channel_id
    }
}


private fun getChannelNameByType(type: NotificationType) = when (type) {
    NotificationType.PROMOTION_SENT -> {
        R.string.promotion_sent_notification_channel_name
    }
    NotificationType.DATABASE_SYNCED -> {
        R.string.database_synced_notification_channel_name
    }
    NotificationType.ORDER_CREATED -> {
        R.string.order_created_notification_channel_name
    }
}

private fun createChannelByType(
    type: NotificationType,
    applicationContext: Context
): NotificationChannel? {
    val channelId = applicationContext.getString(getChannelIdByType(type))
    val channelName = applicationContext.getString(getChannelNameByType(type))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        when (type) {
            NotificationType.PROMOTION_SENT -> {
                val notificationChannel = NotificationChannel(
                    channelId, channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                with(notificationChannel) {
                    description = "Time for breakfast"
                    lightColor = Color.RED
                    enableVibration(true)
                    enableLights(true)
                    setShowBadge(false)
                }
                return notificationChannel
            }
            NotificationType.DATABASE_SYNCED -> {
                val notificationChannel = NotificationChannel(
                    channelId, channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                with(notificationChannel) {
                    description = "Time for breakfast"
                    lightColor = Color.RED
                    enableVibration(true)
                    enableLights(true)
                    setShowBadge(false)
                }
                return notificationChannel

            }
            NotificationType.ORDER_CREATED -> {
                val notificationChannel = NotificationChannel(
                    channelId, channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                with(notificationChannel) {
                    description = "Time for breakfast"
                    lightColor = Color.RED
                    enableVibration(true)
                    enableLights(true)
                    setShowBadge(false)
                }
                return notificationChannel
            }
        }
    }
    return null
}

fun createNotificationChannel(type: NotificationType, applicationContext: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = createChannelByType(type, applicationContext)
        channel?.let {
            val notificationManager =
                applicationContext.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }
}


fun NotificationManager.buildNotification(
    type: NotificationType,
    messageBody: String,
    applicationContext: Context
) {
    val style = createStyleByType(type, applicationContext)
    val channel = getChannelIdByType(type)
    val notificationBuilder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(channel)
    )
        .setSmallIcon(R.drawable.ic_app_notification)
        .setContentText(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setStyle(style)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, notificationBuilder.build())
}
