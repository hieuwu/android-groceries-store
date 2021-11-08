package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.UserRepository

class CheckOutViewModelFactory(
    private val orderId: String, private val orderRepository: OrderRepository, private val userRepository: UserRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckOutViewModel::class.java)) {
            return CheckOutViewModel(orderId, orderRepository, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}