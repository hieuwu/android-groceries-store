package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.domain.models.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun refreshDatabase()
    fun getFromLocal(): Flow<List<RecipeModel>>
}
