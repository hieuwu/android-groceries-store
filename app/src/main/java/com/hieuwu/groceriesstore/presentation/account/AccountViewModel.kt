package com.hieuwu.groceriesstore.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _user: MutableStateFlow<UserModel?> = MutableStateFlow(null)
    val user: MutableStateFlow<UserModel?>
        get() = _user

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getProfileUseCase(GetProfileUseCase.Input()).result.collect {
                _user.value = it
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase(SignOutUseCase.Input())
        }
    }
}
