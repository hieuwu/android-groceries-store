package com.hieuwu.groceriesstore.presentation.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val signOutUseCase: SignOutUseCase
) : ObservableViewModel() {

    private val _user: MutableStateFlow<UserModel?> = MutableStateFlow(null)
    val user: MutableStateFlow<UserModel?>
        get() = _user

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getProfileUseCase.execute(GetProfileUseCase.Input()).result.collect {
                _user.value = it
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.execute(SignOutUseCase.Input())
        }
    }
}
