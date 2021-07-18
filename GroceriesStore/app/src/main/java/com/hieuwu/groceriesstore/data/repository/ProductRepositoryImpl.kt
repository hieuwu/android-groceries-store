package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.data.dao.ProductDao
import com.hieuwu.groceriesstore.di.EntityModelProductMapper
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.ProductEntityModelMapper
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    @EntityModelProductMapper
    @Inject
    lateinit var productModelEntityMapper: ProductEntityModelMapper

    override fun saveAll(products: List<ProductModel>): Boolean {
        for(item in products) {
            val product = productModelEntityMapper.mapToEntity(item)
            saveProduct(product)
        }
        return true
    }

    private fun saveProduct(product: Product) {
        executorService.execute {
            productDao.insert(product)
        }
    }


    override suspend fun getAll(products: List<Product>): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: List<Product>): Product {
        TODO("Not yet implemented")
    }
}