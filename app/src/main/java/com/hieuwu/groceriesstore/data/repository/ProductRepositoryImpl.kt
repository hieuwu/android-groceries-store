package com.hieuwu.groceriesstore.data.repository

import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.di.EntityModelProductMapper
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.ProductEntityModelMapper
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
    private val productDao: ProductDao,
    private val lineItemDao: LineItemDao
) : ProductRepository {

    @EntityModelProductMapper
    @Inject
    lateinit var productModelEntityMapper: ProductEntityModelMapper


    override suspend fun getFromServer() {
        val fireStore = Firebase.firestore
        val productList = mutableListOf<Product>()
        fireStore.collection("products").get().addOnSuccessListener { result ->
            for (document in result) {
                productList.add(getProductEntityFromDocument(document))
            }
        }
            .addOnFailureListener { exception ->
                Timber.w("Error getting documents.${exception}")
            }

        withContext(Dispatchers.IO) {
            productDao.insertAll(productList)
        }
    }

    private fun getProductEntityFromDocument(document: QueryDocumentSnapshot): Product {
        val id = document.id
        val name: String = document.data["name"] as String
        val description: String = document.data["description"] as String
        val price: Number = document.data["price"] as Number
        val image: String = document.data["image"] as String
        val category = document.getDocumentReference("category")
        return Product(id, name, description, price.toDouble(), image, category?.id)
    }

    override suspend fun updateLineItem(lineItem: LineItem) {
        lineItemDao.update(lineItem)
    }

    override suspend fun removeLineItem(lineItem: LineItem) {
        lineItemDao.remove(lineItem)
    }

    override suspend fun searchProductsListByName(name: String) = productDao.searchProductByName(name)


    override suspend fun hasProduct(): Boolean {
        val product = productDao.hasProduct()
        return product != null
    }

    override fun getAllProducts() = productDao.getAll()

    override suspend fun getAllProductsByCategory(categoryId: String) =
        productDao.getAllByCategory(categoryId)

    override fun getProductById(productId: String) = productDao.getById(productId)

}
