package com.hieuwu.groceriesstore.presentation.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.ProductRepository

class ShopViewModelFactory(
    private val repo: ProductRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopViewModel::class.java)) {
            return ShopViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}