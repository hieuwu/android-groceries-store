package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.models.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun refreshDatabase()
    fun getFromLocal(): Flow<List<RecipeModel>>
}
