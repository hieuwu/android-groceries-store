package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.models.OrderModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun createOrUpdate(
        id: String,
        status: String,
        address: String
    )

    suspend fun addLineItem(
        id: Long = 0,
        productId: String,
        orderId: String,
        quantity: Int,
        subtotal: Double
    )

    fun getOneOrderByStatus(status: OrderStatus): Flow<OrderModel?>
    suspend fun sendOrderToServer(order: OrderModel): Boolean

    suspend fun getOrders(): List<OrderModel>
}
