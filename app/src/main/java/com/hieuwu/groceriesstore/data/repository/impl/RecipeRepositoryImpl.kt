package com.hieuwu.groceriesstore.data.repository.impl

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.hieuwu.groceriesstore.data.dao.RecipeDao
import com.hieuwu.groceriesstore.data.dto.asEntity
import com.hieuwu.groceriesstore.data.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.Api
import com.hieuwu.groceriesstore.data.repository.RecipeRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
            val getRecipeDeferred = Api.retrofitService.getRecipesList()
            try {
                val listResult = getRecipeDeferred.await().recipesList.asEntity()
                recipeDao.insertAll(listResult)
            } catch (t: Throwable) {
                var message = t.message
            }
        }
    }

    override fun getFromLocal() = Transformations.map(recipeDao.getAll().asLiveData()) {
        it.asDomainModel()
    }
}
