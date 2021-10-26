package com.hieuwu.groceriesstore.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.UserRepository

class ViewModelFactory(
    private val userRepositoryImpl: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel() as T
        }

        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(userRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}