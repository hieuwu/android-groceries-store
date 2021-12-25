package com.hieuwu.groceriesstore.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.OrderDao
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.utilities.OrderStatus
import com.hieuwu.groceriesstore.utilities.convertOrderEntityToDocument
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
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
        withContext(Dispatchers.IO) {
            orderDao.insert(order)
        }
    }

    override suspend fun addLineItem(lineItem: LineItem) {
        withContext(Dispatchers.IO) {
            lineItemDao.insert(lineItem)
        }
    }

    override fun getOrderById(id: String): OrderWithLineItems {
        return orderDao.getById(id)
    }

    override fun getOrderInCart(status: OrderStatus) = orderDao.getOrderInCart(status.value)

    override fun getOrderWithLineItems() = orderDao.getOrderWithLineItems()

    override fun getCurrentCartId(status: OrderStatus) = orderDao.getCurrentCartId(status.value)

    override fun getCart(status: OrderStatus) = orderDao.getCart(status.value)
    override suspend fun getCartWithLineItems(status: OrderStatus) =
        orderDao.getCartWithLineItems(status.value)

    override suspend fun sendOrderToServer(order: OrderWithLineItems): Boolean {
        val orderMap = convertOrderEntityToDocument(order)
        var isSuccess = false
        val db = Firebase.firestore
        db.collection("orders").document(order.order.id)
            .set(orderMap)
            .addOnSuccessListener {
                isSuccess = true
            }
            .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }
            .await()
        if (isSuccess) {
            withContext(Dispatchers.IO) {
                orderDao.clear()
            }
        }
        return isSuccess
    }




}
