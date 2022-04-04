package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCase

class CheckOutViewModelFactory(
    private val orderId: String,
    private val createOrderUseCase: CreateOrderUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckOutViewModel::class.java)) {
            return CheckOutViewModel(orderId, createOrderUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
