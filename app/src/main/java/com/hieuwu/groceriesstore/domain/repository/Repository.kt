package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface Repository {
    fun saveAll(products: List<ProductModel>): Boolean
    suspend fun getAll():  List<ProductModel>?
    suspend fun getById(id: List<Product>): Product
}