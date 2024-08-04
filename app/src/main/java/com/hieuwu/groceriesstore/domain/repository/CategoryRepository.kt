package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun refreshDatabase()
    fun getFromLocal(): Flow<List<CategoryModel>>
}
