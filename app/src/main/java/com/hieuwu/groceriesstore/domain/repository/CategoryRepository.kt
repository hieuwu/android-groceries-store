package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.domain.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getFromServer()
    fun getFromLocal(): Flow<List<Category>>
}