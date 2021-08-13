package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.di.EntityModelProductMapper
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.ProductEntityModelMapper
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
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

    override suspend fun hasProduct(): Boolean {
        val product = productDao.hasProduct()
        return product != null
    }

    override suspend fun getAllProducts() = productDao.getAll()

    override fun getById(id: String) = productDao.getById(id)

}
