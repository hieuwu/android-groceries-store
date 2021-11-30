package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun hasCart(): Flow<Boolean>?
    suspend fun insert(order: Order)
    suspend fun addLineItem(lineItem: LineItem)
    fun getOrderById(id: String): OrderWithLineItems
    fun getOrderInCart(status: OrderStatus): Flow<Order>
    fun getOrderWithLineItems(): Flow<List<OrderWithLineItems>>
    fun getCurrentCartId(status: OrderStatus): LiveData<String>
    fun getCart(status: OrderStatus): Flow<Order>
    suspend fun getCartWithLineItems(status: OrderStatus): Flow<OrderWithLineItems>
    suspend fun sendOrderToServer(order: OrderWithLineItems)
}