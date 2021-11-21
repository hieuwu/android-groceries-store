package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.repository.RecipeRepository

class OnboardingViewModelFactory(
    private val productRepository: ProductRepository, private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            return OnboardingViewModel(productRepository, categoryRepository, recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}