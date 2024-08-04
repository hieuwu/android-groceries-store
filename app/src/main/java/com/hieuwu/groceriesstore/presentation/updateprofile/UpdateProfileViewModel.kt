package com.hieuwu.groceriesstore.presentation.updateprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.models.UserModel
import com.hieuwu.groceriesstore.usecase.GetProfileUseCase
import com.hieuwu.groceriesstore.usecase.UpdateProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) : ViewModel() {
    private val _user: MutableStateFlow<UserModel?> = MutableStateFlow(null)
    val user: MutableStateFlow<UserModel?>
        get() = _user

    var name: String? = null

    var email: String? = null

    var phoneNumber: String? = null

    var address: String? = null

    private val _isDoneUpdate = MutableLiveData<Boolean>(null)
    val isDoneUpdate: LiveData<Boolean?>
        get() = _isDoneUpdate

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getProfileUseCase(GetProfileUseCase.Input()).result.collect {
                setUserProperties(it)
                _user.value = it
            }
        }
    }

    private fun setUserProperties(user: UserModel?) {
        name = user?.name
        phoneNumber = user?.phone
        email = user?.email
        address = user?.address
    }

    fun updateUserProfile() {
        val id = _user.value!!.id
        try {
            viewModelScope.launch {
                updateProfileUseCase(
                    UpdateProfileUseCase.Input(
                        userId = id,
                        name = name!!,
                        email = email!!,
                        phone = phoneNumber!!,
                        address = address!!
                    )
                )
            }
            _isDoneUpdate.value = true
        } catch (e: Exception) {
            _isDoneUpdate.value = false
        }
    }
}
