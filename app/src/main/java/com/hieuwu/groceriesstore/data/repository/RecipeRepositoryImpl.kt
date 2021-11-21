package com.hieuwu.groceriesstore.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.network.Api
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl : RecipeRepository {
    override suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
            val getRecipeDeferred = Api.retrofitService.getRecipesList()
            try {
                var listResult = getRecipeDeferred.await().recipesList
            } catch (t: Throwable) {
                var message = t.message
            }
        }
    }

    override fun getFromLocal(): LiveData<List<CategoryModel>> {
        //TODO NOT
    }
}