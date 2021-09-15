package com.hieuwu.groceriesstore.presentation.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.shop.ShopViewModel

class ProductListViewModelFactory(
    private val productRepo: ProductRepository,
    private val orderRepo: OrderRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(productRepo, orderRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}