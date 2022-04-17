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
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCase
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NotificationSettingsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationSettingsBinding
    private lateinit var viewModel: NotificationSettingsViewModel

    @Inject
    lateinit var userSettingsUseCase: UserSettingsUseCase

    @Inject
    lateinit var authenticateUserUseCase: AuthenticateUserUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentNotificationSettingsBinding>(
            inflater, R.layout.fragment_notification_settings, container, false
        )

        val viewModelFactory =
            NotificationSettingsViewModelFactory(userSettingsUseCase, authenticateUserUseCase)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NotificationSettingsViewModel::class.java)
        binding.viewModel = viewModel
        setEventListeners()
        viewModel.user.observe(viewLifecycleOwner) {
            viewModel.user.value?.let {
                viewModel.isOrderCreatedNotiEnabled.value = it.isPromotionNotiEnabled
                viewModel.isDatabaseRefreshedNotiEnabled.value = it.isDataRefreshedNotiEnabled
                viewModel.isOrderCreatedNotiEnabled.value = it.isOrderCreatedNotiEnabled
            }
        }
        viewModel.isOrderCreatedNotiEnabled.observe(viewLifecycleOwner) {}
        viewModel.isDatabaseRefreshedNotiEnabled.observe(viewLifecycleOwner) {}
        viewModel.isPromotionNotiEnabled.observe(viewLifecycleOwner) {}

        return binding.root
        // Inflate the layout for this fragment
    }

    private fun setEventListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.switchDatabaseRefreshDone.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isDatabaseRefreshedNotiEnabled.value = isChecked
        }

        binding.switchOrderCreated.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isOrderCreatedNotiEnabled.value = isChecked
        }

        binding.switchPromotion.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isPromotionNotiEnabled?.value = isChecked
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