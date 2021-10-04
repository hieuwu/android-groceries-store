package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository

class ExploreViewModelFactory(
    private val categoryRepo: CategoryRepository,
    private val productRepo: ProductRepository,
    private val orderRepo: OrderRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExploreViewModel::class.java)) {
            return ExploreViewModel(categoryRepo, productRepo, orderRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}