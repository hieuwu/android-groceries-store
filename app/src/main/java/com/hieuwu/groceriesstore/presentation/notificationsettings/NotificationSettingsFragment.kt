package com.hieuwu.groceriesstore.presentation.notificationsettings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentNotificationSettingsBinding
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NotificationSettingsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationSettingsBinding
    private lateinit var viewModel: NotificationSettingsViewModel

    @Inject
    lateinit var userSettingsUseCase: UserSettingsUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentNotificationSettingsBinding>(
            inflater, R.layout.fragment_notification_settings, container, false
        )

        val viewModelFactory = NotificationSettingsViewModelFactory(userSettingsUseCase)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NotificationSettingsViewModel::class.java)
        binding.viewModel = viewModel
        setEventListeners()
        return binding.root
        // Inflate the layout for this fragment
    }

    private fun setEventListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.switchDatabaseRefreshDone.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isDatabaseRefreshedNotiEnabled = isChecked
        }

        binding.switchOrderCreated.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isOrderCreatedNotiEnabled = isChecked
        }

        binding.switchPromotion.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isPromotionNotiEnabled = isChecked
        }

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
}