package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.RecipeModel

interface RecipeRepository {
    suspend fun refreshDatabase()
    fun getFromLocal(): LiveData<List<RecipeModel>>
}