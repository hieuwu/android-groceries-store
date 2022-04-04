package com.hieuwu.groceriesstore.presentation.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase

class ShopViewModelFactory(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopViewModel::class.java)) {
            return ShopViewModel(getProductListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
