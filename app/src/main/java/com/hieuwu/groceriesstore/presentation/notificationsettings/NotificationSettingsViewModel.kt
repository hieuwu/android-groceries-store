package com.hieuwu.groceriesstore.presentation.notificationsettings

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCase
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationSettingsViewModel @Inject constructor(private val userSettingsUseCase: UserSettingsUseCase) :
    ObservableViewModel() {
    @Inject
    lateinit var authenticateUserUseCase: AuthenticateUserUseCase

    private val _user =
        authenticateUserUseCase.getCurrentUser() as MutableLiveData<UserModel?>
    val user: LiveData<UserModel?>
        get() = _user

    private var _isDatabaseRefreshedNotiEnabled: Boolean? = null
    var isDatabaseRefreshedNotiEnabled: Boolean?
        @Bindable
        get() {
            return _isDatabaseRefreshedNotiEnabled
        }
        set(value) {
            _isDatabaseRefreshedNotiEnabled = value
            notifyPropertyChanged(BR.isDatabaseRefreshedNotiEnabled)
        }

    private var _isOrderCreatedNotiEnabled: Boolean? = null
    var isOrderCreatedNotiEnabled: Boolean?
        @Bindable
        get() {
            return _isOrderCreatedNotiEnabled
        }
        set(value) {
            _isOrderCreatedNotiEnabled = value
            notifyPropertyChanged(BR.isOrderCreatedNotiEnabled)
        }

    private var _isPromotionNotiEnabled: Boolean? = null
    var isPromotionNotiEnabled: Boolean?
        @Bindable
        get() {
            return _isPromotionNotiEnabled
        }
        set(value) {
            _isPromotionNotiEnabled = value
            notifyPropertyChanged(BR.isPromotionNotiEnabled)
        }

    init {
//        _isPromotionNotiEnabled = _user.value?.isPromotionNotiEnabled
//        _isDatabaseRefreshedNotiEnabled = _user.value?.isDataRefreshedNotiEnabled
//        _isOrderCreatedNotiEnabled = _user.value?.isOrderCreatedNotiEnabled
    }

    fun updateNotificationSettings() {
        viewModelScope.launch {
            userSettingsUseCase.updateUserSettings(
                user.value?.id!!,
                isOrderCreatedNotiEnabled!!, isDatabaseRefreshedNotiEnabled
                !!, isPromotionNotiEnabled!!
            )
        }
    }

}