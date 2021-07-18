package com.hieuwu.groceriesstore.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.viewmodels.ShopViewModel

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