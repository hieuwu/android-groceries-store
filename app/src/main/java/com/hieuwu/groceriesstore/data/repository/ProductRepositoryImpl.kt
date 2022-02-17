package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Product
import com.hieuwu.groceriesstore.data.entities.asDomainModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.convertProductDocumentToEntity
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
        fireStore.collection(CollectionNames.products).get().addOnSuccessListener { result ->
            for (document in result) {
                productList.add(convertProductDocumentToEntity(document))
            }
        }.addOnFailureListener { exception ->
                Timber.w("Error getting documents.${exception}")
            }.await()

        withContext(Dispatchers.IO) {
            productDao.insertAll(productList)
        }
    }


    override suspend fun updateLineItem(lineItem: LineItem) {
        lineItemDao.update(lineItem)
    }

    override suspend fun updateLineItemQuantityById(quantity: Int, id: Long) {
        lineItemDao.updateQuantityById(quantity, id)
    }

    override suspend fun removeLineItem(lineItem: LineItem) {
        lineItemDao.remove(lineItem)
    }

    override suspend fun removeLineItemById(id: Long) {
        lineItemDao.removeLineItemById(id)

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
