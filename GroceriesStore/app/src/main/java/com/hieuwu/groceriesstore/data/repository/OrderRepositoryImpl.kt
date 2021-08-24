package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.OrderDao
import com.hieuwu.groceriesstore.data.utils.OrderStatus
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val lineItemDao: LineItemDao
) : OrderRepository {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)


    override fun hasCart(): Flow<Boolean> {
        return orderDao.isCartExisted(OrderStatus.IN_CART.value)
    }

    override suspend fun insert(order: Order) {
        executorService.execute {
            orderDao.insert(order)
        }
    }

    override suspend fun addLineItem(lineItem: LineItem) {
        executorService.execute {
            lineItemDao.insert(lineItem)
        }
    }

    override fun getOrderById(id: String): Flow<OrderWithLineItems> {
        return orderDao.getById(id)
    }

    override fun getOrderInCart(status: OrderStatus): Flow<Order> {
        return orderDao.getOrderInCart(status.value)
    }

}