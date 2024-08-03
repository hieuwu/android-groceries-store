package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.models.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun refreshDatabase()
    fun getFromLocal(): Flow<List<RecipeModel>>
}
