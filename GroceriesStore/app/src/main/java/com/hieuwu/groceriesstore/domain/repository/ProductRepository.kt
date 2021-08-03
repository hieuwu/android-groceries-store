package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository : Repository {
    fun getAllProducts(): Flow<List<Product>>
}