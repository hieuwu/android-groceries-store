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
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    override fun saveAll(products: List<ProductModel>): Boolean {
//        for (item in products) {
//            val product = productModelEntityMapper.mapToEntity(item)
//            saveProduct(product)
//        }
        return true
    }

    private fun saveProduct(product: Product) {
        executorService.execute {
            productDao.insert(product)
        }
    }

    override suspend fun getFromServer() {
        var fireStore = Firebase.firestore
        var products = mutableListOf<Product>()

        fireStore.collection("products").get().addOnSuccessListener { result ->
            for (document in result) {
                var id = document.id
                var name: String = document.data["name"] as String
                var description: String = document.data["description"] as String
                var price: Number = document.data["price"] as Number
                var image: String = document.data["image"] as String
                executorService.execute {
                    productDao.insert(Product(id, name, description, price.toDouble(), image))
                }
                Timber.d("${document.id} => ${document.data}")
            }
        }
            .addOnFailureListener { exception ->
                Timber.w("Error getting documents.${exception}")
            }


    }


    override suspend fun getAll(): List<ProductModel>? {
        var products = mutableListOf<Product>()
        var productModelList = mutableListOf<ProductModel>()
//        products = productDao.getAll().toMutableList()
//        for (item in products) {
//            val product = productModelEntityMapper.mapFromEntity(item)
//            productModelList.add(product)
//        }
        return productModelList
    }

    override fun getAllProducts() = productDao.getAll()


    override suspend fun getById(id: List<Product>): Product {
        TODO("Not yet implemented")
    }
}