package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val products: LiveData<List<ProductModel>>
    fun getProductById(productId: String): Flow<Product>
    fun getAllProducts(): LiveData<List<ProductModel>>
    suspend fun getAllProductsByCategory(categoryId: String):LiveData<List<ProductModel>>
    suspend fun getFromServer()
    suspend fun hasProduct(): Boolean
    suspend fun updateLineItem(lineItem: LineItem)
    suspend fun updateLineItemQuantityById(quantity: Int, id: Long)
    suspend fun removeLineItem(lineItem: LineItem)
    suspend fun removeLineItemById(id: Long)
    fun searchProductsListByName(name: String?) : LiveData<List<ProductModel>>
}