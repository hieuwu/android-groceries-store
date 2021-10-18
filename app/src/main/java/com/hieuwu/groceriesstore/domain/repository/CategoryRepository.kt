package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getFromServer()
    fun getFromLocal(): LiveData<List<CategoryModel>>
}