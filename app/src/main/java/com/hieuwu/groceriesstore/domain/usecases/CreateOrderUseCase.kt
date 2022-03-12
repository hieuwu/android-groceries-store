package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.UserModel

interface CreateOrderUseCase {
    suspend fun sendOrderToServer(order: OrderModel): Boolean
    fun getCurrentUser(): LiveData<UserModel?>
    fun setOrderAddress()
    fun getCurrentCart(): LiveData<OrderModel>?
}