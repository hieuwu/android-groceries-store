package com.hieuwu.groceriesstore.presentation.notificationsettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase

class NotificationSettingsViewModelFactory(
    private val userSettingsUseCase: UserSettingsUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationSettingsViewModel::class.java)) {
            return NotificationSettingsViewModel(userSettingsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
