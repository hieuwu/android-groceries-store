package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun createOrUpdate(order: Order)
    suspend fun addLineItem(lineItem: LineItem)
    fun getOneOrderByStatus(status: OrderStatus): Flow<OrderModel?>
    suspend fun sendOrderToServer(order: OrderModel): Boolean
}
