package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val products: LiveData<List<ProductModel>>
    fun getProductById(productId: String): Flow<Product>
    fun getAllProducts(): Flow<List<Product>>
    suspend fun getAllProductsByCategory(categoryId: String): Flow<List<Product>>
    suspend fun getFromServer()
    suspend fun hasProduct(): Boolean
    suspend fun updateLineItem(lineItem: LineItem)
    suspend fun removeLineItem(lineItem: LineItem)
    fun searchProductsListByName(name: String?) : Flow<List<Product>>
}