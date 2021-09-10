package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.OrderRepository

class CheckOutViewModelFactory(
    private val orderId: String, private val orderRepository: OrderRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckOutViewModel::class.java)) {
            return CheckOutViewModel(orderId, orderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}