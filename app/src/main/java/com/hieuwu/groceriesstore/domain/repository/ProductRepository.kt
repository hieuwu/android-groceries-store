package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val products: Flow<List<ProductModel>>
    fun getProductById(productId: String): Flow<ProductModel>
    fun getAllProductsByCategory(categoryId: String): Flow<List<ProductModel>>
    suspend fun refreshDatabase()
    suspend fun updateLineItemQuantityById(quantity: Int, id: Long)
    suspend fun removeLineItemById(id: Long)
    fun searchProductsListByName(name: String?): Flow<List<ProductModel>>
}
