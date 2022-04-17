package com.hieuwu.groceriesstore.presentation.notificationsettings

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCase
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class NotificationSettingsViewModel @Inject constructor(
    private val userSettingsUseCase: UserSettingsUseCase,
    private val authenticateUserUseCase: AuthenticateUserUseCase
) :
    ObservableViewModel() {
    private val _user =
        authenticateUserUseCase.getCurrentUser() as MutableLiveData<UserModel?>
    val user: LiveData<UserModel?>
        get() = _user

    private var _isDatabaseRefreshedNotiEnabled = MutableLiveData(false)
    var isDatabaseRefreshedNotiEnabled: MutableLiveData<Boolean> = _isDatabaseRefreshedNotiEnabled
        set(value) {
            _isDatabaseRefreshedNotiEnabled.value = value.value
            field = value
        }
    private var _isOrderCreatedNotiEnabled = MutableLiveData(false)
    var isOrderCreatedNotiEnabled: MutableLiveData<Boolean> = _isOrderCreatedNotiEnabled
        set(value) {
            _isOrderCreatedNotiEnabled.value = value.value
            field = value
        }

    private var _isPromotionNotiEnabled = MutableLiveData(false)
    var isPromotionNotiEnabled: MutableLiveData<Boolean> = _isOrderCreatedNotiEnabled
        set(value) {
            _isPromotionNotiEnabled.value = value.value
            field = value
        }

    init {
        _user.value?.let {
            _isPromotionNotiEnabled.value = it.isPromotionNotiEnabled
            _isDatabaseRefreshedNotiEnabled.value = it.isDataRefreshedNotiEnabled
            _isOrderCreatedNotiEnabled.value = it.isOrderCreatedNotiEnabled
        }
    }

    fun updateNotificationSettings() {
        Timber.d("promo: $isPromotionNotiEnabled, ord: $isOrderCreatedNotiEnabled, db: $isDatabaseRefreshedNotiEnabled")
        viewModelScope.launch {
            userSettingsUseCase.updateUserSettings(
                user.value?.id!!,
                isOrderCreatedNotiEnabled?.value!!,
                isDatabaseRefreshedNotiEnabled?.value!!,
                isPromotionNotiEnabled?.value!!
            )
        }
    }

}