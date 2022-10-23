package com.hieuwu.groceriesstore.presentation.authentication

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
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

    fun signIn() {
        viewModelScope.launch {
            _isSignUpSuccessful.value = signInUseCase.execute(
                SignInUseCase.Input(
                    email = _email!!,
                    password = _password!!
                )
            ).result
        }
    }
}
