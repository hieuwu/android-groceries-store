package com.hieuwu.groceriesstore.presentation.notificationsettings

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentNotificationSettingsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationSettingsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationSettingsBinding
    private val viewModel: NotificationSettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentNotificationSettingsBinding>(
            inflater, R.layout.fragment_notification_settings, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setEventListeners()
        setObservers()

        return binding.root
    }

    private fun setObservers() {
        viewModel.user.observe(viewLifecycleOwner) { viewModel.initializeSwitchValue(it) }
        viewModel.isOrderCreatedNotiEnabled.observe(viewLifecycleOwner) {}
        viewModel.isDatabaseRefreshedNotiEnabled.observe(viewLifecycleOwner) {}
        viewModel.isPromotionNotiEnabled.observe(viewLifecycleOwner) {}
    }

    private fun setEventListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        createChannel(
            getString(R.string.order_created_notification_channel_id),
            getString(R.string.order_created_notification_channel_name))

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> {
                    // Update user data to backend
                    viewModel.updateNotificationSettings()
                    true
                }
                else -> false
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