package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun saveAll(products: List<ProductModel>): Boolean
    suspend fun getAll(): List<ProductModel>?
    suspend fun getById(id: List<Product>): Product
    fun getAllProducts(): Flow<List<Product>>
    suspend fun getFromServer()
}