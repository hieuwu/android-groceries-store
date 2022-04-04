package com.hieuwu.groceriesstore.presentation.updateprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.AuthenticateUserUseCase
import com.hieuwu.groceriesstore.presentation.account.AccountViewModel

class UpdateProfileViewModelFactory(private val authenticateUserUseCase: AuthenticateUserUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateProfileViewModel::class.java)) {
            return UpdateProfileViewModel(authenticateUserUseCase) as T
        }
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(authenticateUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
