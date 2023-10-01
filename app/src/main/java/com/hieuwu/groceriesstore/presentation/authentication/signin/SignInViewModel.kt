package com.hieuwu.groceriesstore.presentation.authentication.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _isSignUpSuccessful = MutableSharedFlow<Unit>()
    val isSignUpSuccessful: SharedFlow<Unit> = _isSignUpSuccessful

    private val _showAccountNotExistedError = MutableSharedFlow<Unit>()
    val showAccountNotExistedError: SharedFlow<Unit> = _showAccountNotExistedError

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onRemoveText() {
        _email.value = ""
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun signIn() {
        viewModelScope.launch {
            when (signInUseCase.execute(
                SignInUseCase.Input(
                    email = _email.value,
                    password = _password.value
                )
            )) {
                is SignInUseCase.Output.Error -> {
                    //TODO Handle show general error
                }

                is SignInUseCase.Output.AccountNotExistedError -> {
                    _showAccountNotExistedError.emit(Unit)
                }

                else -> {
                    _isSignUpSuccessful.emit(Unit)
                }
            }
        }
    }
}
