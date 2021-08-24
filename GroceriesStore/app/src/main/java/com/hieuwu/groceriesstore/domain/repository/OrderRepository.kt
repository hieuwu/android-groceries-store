package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun hasCart(): Flow<Boolean>
    suspend fun insert(order: Order)
    suspend fun addLineItem(lineItem: LineItem)
    fun getOrderById(id: String): Flow<OrderWithLineItems>
    suspend fun getOrderInCart(): Flow<Order>
}