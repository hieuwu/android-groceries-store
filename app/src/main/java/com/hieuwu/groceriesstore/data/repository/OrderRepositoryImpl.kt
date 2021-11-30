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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
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

    override fun getOrderById(id: String): OrderWithLineItems {
        return orderDao.getById(id)
    }

    override fun getOrderInCart(status: OrderStatus) = orderDao.getOrderInCart(status.value)

    override fun getOrderWithLineItems() = orderDao.getOrderWithLineItems()

    override fun getCurrentCartId(status: OrderStatus) = orderDao.getCurrentCartId(status.value)

    override fun getCart(status: OrderStatus) = orderDao.getCart(status.value)
    override suspend fun getCartWithLineItems(status: OrderStatus) =
        orderDao.getCartWithLineItems(status.value)

    override suspend fun sendOrderToServer(order: OrderWithLineItems) {
        //Convert order with line items to order on firebase
        var orderMap = HashMap<String, Any>()
        orderMap["address"] = order.order.address

        val db = Firebase.firestore
        db.collection("orders").document(order.order.id)
            .set(orderMap)
            .addOnSuccessListener {


            }
            .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }
            .await()
    }
}
