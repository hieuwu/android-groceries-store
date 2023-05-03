package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.data.repository.ProductRepository
import com.hieuwu.groceriesstore.data.repository.RecipeRepository
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RefreshAppDataUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository
) : RefreshAppDataUseCase {
    override suspend fun execute(input: Unit) {
        withContext(Dispatchers.IO) {
            categoryRepository.refreshDatabase()
            productRepository.refreshDatabase()
            recipeRepository.refreshDatabase()
        }
    }
}
