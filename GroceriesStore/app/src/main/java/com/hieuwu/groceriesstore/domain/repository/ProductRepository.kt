package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getById(id: List<Product>): Product
    fun getAllProducts(): Flow<List<Product>>
    suspend fun getFromServer()
}