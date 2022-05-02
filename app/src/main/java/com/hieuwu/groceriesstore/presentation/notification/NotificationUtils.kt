package com.hieuwu.groceriesstore.presentation.notification

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
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

private fun getChannelIdByType(type: NotificationType): Int = when (type) {
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
