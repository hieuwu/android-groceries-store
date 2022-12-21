package com.hieuwu.groceriesstore.presentation.authentication

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) :
    ObservableViewModel() {

    private val _isSignUpSuccessful = MutableSharedFlow<Boolean?>()
    val isSignUpSuccessful: SharedFlow<Boolean?> = _isSignUpSuccessful

    private val _showAccountNotExistedError = MutableSharedFlow<Boolean>()
    val showAccountNotExistedError: SharedFlow<Boolean> = _showAccountNotExistedError

    private var _email: String? = null
    var email: String?
        @Bindable
        get() {
            return _email
        }
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    private var _password: String? = null
    var password: String?
        @Bindable
        get() {
            return _password
        }
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }

    fun signIn() {
        viewModelScope.launch {
            val res = signInUseCase.execute(
                SignInUseCase.Input(
                    email = _email!!,
                    password = _password!!
                )
            )

            when (res) {
                is SignInUseCase.Output.Error -> {
                    //TODO Handle show general error
                }
                is SignInUseCase.Output.AccountNotExistedError -> {
                    _showAccountNotExistedError.emit(true)
                    _isSignUpSuccessful.emit(false)
                }
                else -> {
                    _isSignUpSuccessful.emit(true)
                }
            }

        }
    }
}
