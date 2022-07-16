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
    return when (type) {
        NotificationType.PROMOTION_SENT -> {
            val bannerImage =
                BitmapFactory.decodeResource(applicationContext.resources, R.drawable.banner)
            NotificationCompat.BigPictureStyle().bigPicture(bannerImage)
        }
        NotificationType.DATABASE_SYNCED -> {
            NotificationCompat.BigTextStyle().bigText("You are up-to-date with new products! Let's discover!")
                .setSummaryText("New products have arrived!")
                .setBigContentTitle("New day new products!")
        }
        NotificationType.ORDER_CREATED -> {
            NotificationCompat.InboxStyle()
                .setBigContentTitle("Order is ready!")
                .setSummaryText("About 30 minutes")
                .addLine("Be ready to receive it")
                .addLine("We have more for you! See you next Time")
                .addLine("You received this notification when the order is ready")

        }
    }
}

private fun getPairOfChannelNameAndId(type: NotificationType) = when (type) {
    NotificationType.PROMOTION_SENT -> {
        Pair(
            R.string.promotion_sent_notification_channel_name,
            R.string.promotion_sent_notification_channel_id
        )
    }
    NotificationType.DATABASE_SYNCED -> {
        Pair(
            R.string.database_synced_notification_channel_name,
            R.string.database_synced_notification_channel_id
        )
    }
    NotificationType.ORDER_CREATED -> {
        Pair(
            R.string.order_created_notification_channel_name,
            R.string.order_created_notification_channel_id
        )
    }
}

private fun createChannelByType(
    type: NotificationType,
    applicationContext: Context
): NotificationChannel? {
    val channelName = applicationContext.getString(getPairOfChannelNameAndId(type).first)
    val channelId = applicationContext.getString(getPairOfChannelNameAndId(type).second)
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


fun NotificationManager.showNotification(
    type: NotificationType,
    messageBody: String,
    applicationContext: Context
) {
    val style = createStyleByType(type, applicationContext)
    val channel = applicationContext.getString(getPairOfChannelNameAndId(type).second)

    val notificationBuilder = NotificationCompat.Builder(
        applicationContext, channel
    )
        .setSmallIcon(R.drawable.ic_baseline_check_circle_24)
        .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
        .setContentText(messageBody)
        .setStyle(style)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, notificationBuilder.build())
}

