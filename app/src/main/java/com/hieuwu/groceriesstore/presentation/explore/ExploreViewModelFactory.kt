package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.ExploreProductUseCase

class ExploreViewModelFactory(
    private val exploreProductUseCase: ExploreProductUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExploreViewModel::class.java)) {
            return ExploreViewModel(exploreProductUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
