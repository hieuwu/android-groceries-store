package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.domain.entities.Product

interface Repository {
    suspend fun saveAll(products: List<Product>): Boolean
    suspend fun getAll(products: List<Product>): List<Product>
    suspend fun getById(id: List<Product>): Product
}