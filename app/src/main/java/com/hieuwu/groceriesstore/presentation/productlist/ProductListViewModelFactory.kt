package com.hieuwu.groceriesstore.presentation.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase

class ProductListViewModelFactory(
    private val categoryId: String,
    private val getProductListUseCase: GetProductListUseCase

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(categoryId, getProductListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
