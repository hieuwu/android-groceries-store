package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.ProductModelToEntity
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    @Inject
    lateinit var productMapper: ProductModelToEntity

    @Inject
    lateinit var productDao: ProductDao
    override suspend fun saveAll(products: List<Product>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(products: List<Product>): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: List<Product>): Product {
        TODO("Not yet implemented")
    }
}