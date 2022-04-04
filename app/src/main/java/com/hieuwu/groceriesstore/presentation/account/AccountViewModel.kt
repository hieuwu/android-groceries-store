package com.hieuwu.groceriesstore.presentation.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

class AccountViewModel @Inject constructor(private val authenticateUserUseCase: AuthenticateUserUseCase) :
    ObservableViewModel() {

    private val _user = authenticateUserUseCase.getCurrentUser() as MutableLiveData<UserModel?>
    val user: LiveData<UserModel?>
        get() = _user

    fun signOut() {
        viewModelScope.launch {
            authenticateUserUseCase.signOut()
        }
    }
}
