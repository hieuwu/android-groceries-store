package com.hieuwu.groceriesstore.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.data.utils.OrderStatus
import com.hieuwu.groceriesstore.di.EntityModelProductMapper
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.domain.mapper.ProductEntityModelMapper
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val lineItemDao: LineItemDao
) : ProductRepository {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    @EntityModelProductMapper
    @Inject
    lateinit var productModelEntityMapper: ProductEntityModelMapper


    override suspend fun getFromServer() {
        var fireStore = Firebase.firestore
        fireStore.collection("products").get().addOnSuccessListener { result ->
            for (document in result) {
                val id = document.id
                val name: String = document.data["name"] as String
                val description: String = document.data["description"] as String
                val price: Number = document.data["price"] as Number
                val image: String = document.data["image"] as String
                executorService.execute {
                    productDao.insert(Product(id, name, description, price.toDouble(), image))
                }
            }
        }
            .addOnFailureListener { exception ->
                Timber.w("Error getting documents.${exception}")
            }
    }

    override suspend fun updateProductAndLineItem(lineItem: ProductAndLineItem) {
        lineItemDao.update(lineItem.lineItem)
    }

    override suspend fun hasProduct(): Boolean {
        val product = productDao.hasProduct()
        return product != null
    }


    override suspend fun getAllProducts() = productDao.getAll()

    override fun getById(id: String) = productDao.getById(id)

    override suspend fun getAllLineItem() = lineItemDao.getAll()
    override suspend fun removeProductAndLineItem(lineItem: LineItem) {
        lineItemDao.removeCurrentItem(lineItem)
    }

    override suspend fun getLineItemInOrder(orderId: String) =
        lineItemDao.getLineItemInOrder(orderId)
}
