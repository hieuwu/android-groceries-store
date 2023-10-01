package com.hieuwu.groceriesstore.presentation.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private var _email = MutableStateFlow("")
    var email: StateFlow<String> = _email

    private var _password = MutableStateFlow("")
    var password: StateFlow<String> = _password

    private var _name = MutableStateFlow("")
    var name: StateFlow<String> = _name

    private val _showSignUpSuccessMessage = MutableSharedFlow<Unit>()
    val showSignUpSuccessMessage: SharedFlow<Unit> = _showSignUpSuccessMessage

    private val _showSignUpErrorMessage = MutableSharedFlow<Unit>()
    val showSignUpErrorMessage: SharedFlow<Unit> = _showSignUpErrorMessage

    fun onEmailChange(text: String) {
        _email.value = text
    }

    fun onEmailRemove() {
        _email.value = ""
    }

    fun onNameRemove() {
        _name.value = ""
    }

    fun onPasswordChange(text: String) {
        _password.value = text
    }

    fun onNameChange(text: String) {
        _name.value = text
    }

    fun createAccount() {
        viewModelScope.launch {
            val result = userRepository.createAccount(_email.value, _password.value, _name.value)
            if (result) {
                _showSignUpSuccessMessage.emit(Unit)
            } else {
                _showSignUpErrorMessage.emit(Unit)
            }
        }
    }
}
