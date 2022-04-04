package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.OrderDao
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.data.entities.asDomainModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.OrderStatus
import com.hieuwu.groceriesstore.utilities.convertOrderEntityToDocument
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber

@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val lineItemDao: LineItemDao
) : OrderRepository {

    override suspend fun createOrUpdate(order: Order) {
        withContext(Dispatchers.IO) {
            orderDao.insert(order)
        }
    }

    override suspend fun addLineItem(lineItem: LineItem) {
        withContext(Dispatchers.IO) {
            lineItemDao.insert(lineItem)
        }
    }

    override fun getOneOrderByStatus(status: OrderStatus): LiveData<OrderModel>? =
        Transformations.map(orderDao.getCartWithLineItems(status.value)?.asLiveData()) {
            it?.asDomainModel()
        }

    override suspend fun sendOrderToServer(order: OrderModel): Boolean {
        val orderMap = convertOrderEntityToDocument(order)
        var isSuccess = false
        val db = Firebase.firestore

        db.collection(CollectionNames.orders).document(order.id)
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
