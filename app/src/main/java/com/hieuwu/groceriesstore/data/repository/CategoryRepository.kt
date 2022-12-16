package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.CategoryModel

interface CategoryRepository {
    suspend fun refreshDatabase()
    fun getFromLocal(): LiveData<List<CategoryModel>>
}
