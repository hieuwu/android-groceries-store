package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository

class ExploreViewModelFactory(
    private val categoryRepository: CategoryRepository,
    private val productRepo: ProductRepository,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExploreViewModel::class.java)) {
            return ExploreViewModel(categoryRepository, productRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}