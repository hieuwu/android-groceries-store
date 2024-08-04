package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.repository.RecipeRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RefreshAppDataUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : RefreshAppDataUseCase {
    override suspend fun invoke(input: Unit) {
        withContext(ioDispatcher) {
            categoryRepository.refreshDatabase()
            productRepository.refreshDatabase()
            recipeRepository.refreshDatabase()
        }
    }
}
