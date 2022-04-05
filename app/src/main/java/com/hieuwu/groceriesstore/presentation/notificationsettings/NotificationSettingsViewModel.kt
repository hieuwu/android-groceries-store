package com.hieuwu.groceriesstore.presentation.notificationsettings

import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject

class NotificationSettingsViewModel @Inject constructor(private val userSettingsUseCase: UserSettingsUseCase) :
    ObservableViewModel() {
    fun updateNotificationSettings() {
        TODO("Not yet implemented")
    }

}