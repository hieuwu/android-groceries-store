package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.OrderDao
import com.hieuwu.groceriesstore.data.utils.OrderStatus
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val lineItemDao: LineItemDao
) : OrderRepository {
    override fun hasCart(): Flow<Boolean> {
        return orderDao.isCartExisted(OrderStatus.IN_CART.value)
    }

    override suspend fun insert(order: Order) {
        orderDao.insert(order)
    }

    override suspend fun addLineItem(lineItem: LineItem) {
        lineItemDao.insert(lineItem)
    }

    override fun getOrderById(id: String): Flow<OrderWithLineItems>{
        return orderDao.getById(id)
    }

    override suspend fun getOrderInCart(): Flow<Order> {
        return orderDao.getOrderInCart()
    }


}