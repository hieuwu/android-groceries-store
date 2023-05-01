package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.database.dao.RecipeDao
import com.hieuwu.groceriesstore.data.database.entities.asDomainModel
import com.hieuwu.groceriesstore.data.network.Api
import com.hieuwu.groceriesstore.data.network.dto.asEntity
import com.hieuwu.groceriesstore.data.repository.RecipeRepository
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun refreshDatabase() {
        try {
            val getRecipeDeferred = Api.retrofitService.getRecipesList()
            val listResult = getRecipeDeferred.await().recipesList.asEntity()
            recipeDao.insertAll(listResult)
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }

    override fun getFromLocal() = recipeDao.getAll().map { it.asDomainModel() }
}
