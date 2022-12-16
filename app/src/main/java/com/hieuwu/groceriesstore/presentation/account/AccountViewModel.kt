package com.hieuwu.groceriesstore.presentation.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val signOutUseCase: SignOutUseCase
) : ObservableViewModel() {

    private val _user: MutableLiveData<UserModel?> = MutableLiveData()
    val user: LiveData<UserModel?>
        get() = _user

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _user.value = getProfileUseCase.execute(GetProfileUseCase.Input()).result.value
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.execute(SignOutUseCase.Input())
        }
    }
}
