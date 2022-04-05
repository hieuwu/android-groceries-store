package com.hieuwu.groceriesstore.presentation.notificationsettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import com.hieuwu.groceriesstore.presentation.onboarding.OnboardingViewModel

class NotificationSettingsViewModelFactory(
    private val refreshAppDataUseCase: RefreshAppDataUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            return NotificationSettingsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
