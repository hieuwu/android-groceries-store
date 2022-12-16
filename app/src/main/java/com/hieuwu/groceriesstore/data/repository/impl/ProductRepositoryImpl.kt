package com.hieuwu.groceriesstore.data.repository.impl

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.data.dao.LineItemDao
import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.data.entities.Product
import com.hieuwu.groceriesstore.data.entities.asDomainModel
import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.utilities.CollectionNames
import com.hieuwu.groceriesstore.utilities.convertProductDocumentToEntity
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val lineItemDao: LineItemDao
) : ProductRepository {

    override val products: Flow<List<ProductModel>> =
        productDao.getAll().map {
            it.asDomainModel()
        }

    override suspend fun refreshDatabase() {
        val fireStore = Firebase.firestore
        val productList = mutableListOf<Product>()
        fireStore.collection(CollectionNames.products).get().addOnSuccessListener { result ->
            for (document in result) {
                productList.add(convertProductDocumentToEntity(document))
            }
        }.addOnFailureListener { exception ->
                Timber.w("Error getting documents.$exception")
            }.await()

        withContext(Dispatchers.IO) {
            productDao.insertAll(productList)
        }
    }

    override suspend fun updateLineItemQuantityById(quantity: Int, id: Long) {
        withContext(Dispatchers.IO) {
            lineItemDao.updateQuantityById(quantity, id)
        }
    }

    override suspend fun removeLineItemById(id: Long) {
        withContext(Dispatchers.IO) {
            lineItemDao.removeLineItemById(id)
        }
    }

    override fun searchProductsListByName(name: String?) =
        Transformations.map(productDao.searchProductByName(name).asLiveData()) {
            it.asDomainModel()
        }

    override fun getAllProducts() = Transformations.map(productDao.getAll().asLiveData()) {
        it.asDomainModel()
    }

    override fun getAllProductsByCategory(categoryId: String) =
        productDao.getAllByCategory(categoryId).map {
            it.asDomainModel()
        }

    override fun getProductById(productId: String) =
        Transformations.map(productDao.getById(productId).asLiveData()) {
            it.asDomainModel()
        }
}
