package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface Repository {
    fun saveAll(products: List<ProductModel>): Boolean
    suspend fun getAll(products: List<Product>): List<Product>
    suspend fun getById(id: List<Product>): Product
}