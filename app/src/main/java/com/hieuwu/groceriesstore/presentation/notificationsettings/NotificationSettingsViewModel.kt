package com.hieuwu.groceriesstore.presentation.notificationsettings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotificationSettingsViewModel @Inject constructor(
    private val userSettingsUseCase: UserSettingsUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) :
    ObservableViewModel() {
    private val _user: MutableStateFlow<UserModel?> = MutableStateFlow(null)
    val user: MutableStateFlow<UserModel?>
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
    var isPromotionNotiEnabled: MutableLiveData<Boolean> = _isPromotionNotiEnabled
        set(value) {
            _isPromotionNotiEnabled.value = value.value
            field = value
        }

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getProfileUseCase.execute(GetProfileUseCase.Input()).result.collect {
                _user.value = it
            }
        }
    }

    fun initializeSwitchValue(user: UserModel?) {
        user?.let {
            isPromotionNotiEnabled.value = it.isPromotionNotiEnabled
            isDatabaseRefreshedNotiEnabled.value = it.isDataRefreshedNotiEnabled
            isOrderCreatedNotiEnabled.value = it.isOrderCreatedNotiEnabled
        }
    }

    fun updateNotificationSettings() {
        Timber.d("promo: $isPromotionNotiEnabled, ord: $isOrderCreatedNotiEnabled, db: $isDatabaseRefreshedNotiEnabled")
        viewModelScope.launch {
            userSettingsUseCase.execute(
                UserSettingsUseCase.Input(
                    user.value?.id!!,
                    isOrderCreatedNotiEnabled.value!!,
                    isDatabaseRefreshedNotiEnabled.value!!,
                    isPromotionNotiEnabled.value!!
                )
            )
        }
    }

}