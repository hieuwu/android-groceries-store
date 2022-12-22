package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.database.dao.RecipeDao
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.Api
import com.hieuwu.groceriesstore.data.network.dto.asEntity
import com.hieuwu.groceriesstore.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

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

    override fun getFromLocal() = recipeDao.getAll().map { it.asDomainModel()}
}
