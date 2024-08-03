package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.data.database.entities.LineItem
import com.hieuwu.groceriesstore.data.database.entities.Order
import com.hieuwu.groceriesstore.core.models.OrderModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun createOrUpdate(order: Order)
    suspend fun addLineItem(lineItem: LineItem)
    fun getOneOrderByStatus(status: OrderStatus): Flow<OrderModel?>
    suspend fun sendOrderToServer(order: OrderModel): Boolean

    suspend fun getOrders(): List<OrderModel>
}
