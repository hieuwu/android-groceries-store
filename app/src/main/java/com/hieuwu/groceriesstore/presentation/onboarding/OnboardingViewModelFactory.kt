package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase

class OnboardingViewModelFactory(
    private val refreshAppDataUseCase: RefreshAppDataUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            return OnboardingViewModel(refreshAppDataUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}