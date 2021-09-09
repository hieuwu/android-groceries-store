package com.hieuwu.groceriesstore.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.presentation.viewmodels.CartViewModel
import com.hieuwu.groceriesstore.presentation.viewmodels.CheckOutViewModel

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