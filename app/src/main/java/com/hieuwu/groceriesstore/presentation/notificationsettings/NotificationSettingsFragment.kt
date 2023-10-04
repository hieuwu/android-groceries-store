package com.hieuwu.groceriesstore.presentation.notificationsettings

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationSettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createChannel(
            getString(R.string.order_created_notification_channel_id),
            getString(R.string.order_created_notification_channel_name)
        )
        return ComposeView(requireContext()).apply {
            setContent {
                NotificationSettingsScreen(
                   onNavigateUp = { findNavController().navigateUp() }
                )
            }
        }
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
            val notificationManager = activity?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(notificationChannel)
        }

    }
}