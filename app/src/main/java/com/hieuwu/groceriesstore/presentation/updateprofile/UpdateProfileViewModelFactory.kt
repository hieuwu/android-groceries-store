package com.hieuwu.groceriesstore.presentation.updateprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.presentation.account.AccountViewModel

class UpdateProfileViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateProfileViewModel::class.java)) {
            return UpdateProfileViewModel(userRepository) as T
        }
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}