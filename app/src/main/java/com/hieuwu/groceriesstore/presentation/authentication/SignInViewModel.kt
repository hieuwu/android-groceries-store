package com.hieuwu.groceriesstore.presentation.authentication

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) :
    ObservableViewModel() {

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

    private val _isSignUpSuccessful = MutableLiveData<Boolean?>()
    val isSignUpSuccessful: LiveData<Boolean?>
        get() = _isSignUpSuccessful

    private val _showAccountNotExistedError = MutableStateFlow(false)
    val showAccountNotExistedError: StateFlow<Boolean> = _showAccountNotExistedError

    fun signIn() {
        viewModelScope.launch {
            val res = signInUseCase.execute(
                SignInUseCase.Input(
                    email = _email!!,
                    password = _password!!
                )
            )

            when(res) {
                is SignInUseCase.Output.Error -> {
                    //TODO Handle show general error
                }
                is SignInUseCase.Output.AccountNotExistedError -> {
                    //TODO Handle show account not existed error
                    _showAccountNotExistedError.value = true
                    _isSignUpSuccessful.value = false
                }
            }

        }
    }
}
