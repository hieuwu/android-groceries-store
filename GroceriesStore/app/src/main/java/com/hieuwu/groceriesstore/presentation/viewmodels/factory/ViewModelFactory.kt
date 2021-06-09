package com.hieuwu.groceriesstore.presentation.viewmodels.factory

import android.animation.Animator
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.data.GroceriesStoreDatabase
import com.hieuwu.groceriesstore.presentation.viewmodels.SignInViewModel
import com.hieuwu.groceriesstore.presentation.viewmodels.SignUpViewModel

class ViewModelFactory(
    private val valdataSource: GroceriesStoreDatabase?,
    private val application: Application?
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel() as T
        }

        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}