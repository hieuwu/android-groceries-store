package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase

class CartViewModelFactory(
    private val updateCartItemUseCase: UpdateCartItemUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(updateCartItemUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}