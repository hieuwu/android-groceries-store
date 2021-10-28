package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.entities.asDomainModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val lineItemDao: LineItemDao
) : ProductRepository {


    override val products: LiveData<List<ProductModel>> =
        Transformations.map(productDao.getAll().asLiveData()) {
            it.asDomainModel()
        }

    override suspend fun getFromServer() {
        val fireStore = Firebase.firestore
        val productList = mutableListOf<Product>()
        fireStore.collection("products").get().addOnSuccessListener { result ->
            for (document in result) {
                productList.add(getProductEntityFromDocument(document))
            }
        }.addOnFailureListener { exception ->
                Timber.w("Error getting documents.${exception}")
            }.await()

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

    override fun searchProductsListByName(name: String?) =
        Transformations.map(productDao.searchProductByName(name).asLiveData()) {
            it.asDomainModel()
        }

    override suspend fun hasProduct(): Boolean {
        val product = productDao.hasProduct()
        return product != null
    }

    override fun getAllProducts() = Transformations.map(productDao.getAll().asLiveData()) {
        it.asDomainModel()
    }

    override suspend fun getAllProductsByCategory(categoryId: String) =
        Transformations.map(productDao.getAllByCategory(categoryId).asLiveData()) {
            it.asDomainModel()
        }

    override fun getProductById(productId: String) = productDao.getById(productId)

}
