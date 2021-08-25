package com.hieuwu.groceriesstore.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.viewmodels.CartViewModel

class CartViewModelFactory(private val productRepository: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}