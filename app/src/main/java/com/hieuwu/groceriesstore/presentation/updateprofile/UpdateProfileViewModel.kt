package com.hieuwu.groceriesstore.presentation.updateprofile

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.UpdateProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UpdateProfileViewModel(
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UpdateProfileUiState())
    val uiState: StateFlow<UpdateProfileUiState> = _uiState.asStateFlow()

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getProfileUseCase(GetProfileUseCase.Input()).result.collect { profile ->
                _uiState.update { it.copy(user = profile) }
            }
        }
    }

    fun updateName(name: String) {
        _uiState.update {
            it.copy(
                user = it.user?.copy(name = name)
            )
        }
    }

    fun updatePhone(phone: String) {
        _uiState.update {
            it.copy(
                user = it.user?.copy(phone = phone)
            )
        }
    }

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(
                user = it.user?.copy(email = email),
                isInvalidEmail = false,
            )
        }
    }

    fun updateAddress(address: String) {
        _uiState.update {
            it.copy(
                user = it.user?.copy(address = address)
            )
        }
    }

    fun updateUserProfile() {
        val userProfile = uiState.value.user ?: return
        if (!validateEmail(userProfile.email.orEmpty())) {
            return
        }
        val id = userProfile.id
        _uiState.update { it.copy(isLoading = true) }
        try {
            viewModelScope.launch(Dispatchers.IO) {
                updateProfileUseCase(
                    UpdateProfileUseCase.Input(
                        userId = id,
                        name = userProfile.name!!,
                        email = userProfile.email!!,
                        phone = userProfile.phone,
                        address = userProfile.address,
                    )
                )
                _uiState.update { it.copy(isUpdateSuccess = true, isLoading = false) }
            }
        } catch (e: Exception) {
            _uiState.update { it.copy(isUpdateSuccess = false, isLoading = false) }
        }
    }

    private fun validateEmail(email: String): Boolean {
        val isEmailValid = EMAIL_ADDRESS.matcher(email).matches()
        if (!isEmailValid) {
            _uiState.update { it.copy(isInvalidEmail = true) }
        }
        return isEmailValid
    }

    fun onShowMessage() {
        _uiState.update { it.copy(isUpdateSuccess = null) }
    }
}
