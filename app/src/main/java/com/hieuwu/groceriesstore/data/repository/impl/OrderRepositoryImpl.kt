package com.hieuwu.groceriesstore.data.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.database.dao.LineItemDao
import com.hieuwu.groceriesstore.data.database.dao.OrderDao
import com.hieuwu.groceriesstore.data.database.entities.LineItem
import com.hieuwu.groceriesstore.data.database.entities.Order
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.OrderStatus
import com.hieuwu.groceriesstore.utilities.SupabaseMapper
import com.hieuwu.groceriesstore.utilities.convertOrderEntityToDocument
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber

@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val lineItemDao: LineItemDao,
    private val postgrest: Postgrest
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

    override fun getOneOrderByStatus(status: OrderStatus): Flow<OrderModel?> =
        orderDao.getCartWithLineItems(status.value).map {
            it?.asDomainModel()
        }

    override suspend fun sendOrderToServer(order: OrderModel): Boolean {
        val orderMap = convertOrderEntityToDocument(order)
        val orderDto = SupabaseMapper.mapModelToDto(order)
        var isSuccess = false
        val db = Firebase.firestore

        postgrest[CollectionNames.orders].insert(orderDto)
        db.collection(CollectionNames.orders).document(orderDto.id)
            .set(orderMap)
            .addOnSuccessListener {
                isSuccess = true
            }
            .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }
            .await()
        orderDao.clear()
        isSuccess = true
        return isSuccess
    }
}
