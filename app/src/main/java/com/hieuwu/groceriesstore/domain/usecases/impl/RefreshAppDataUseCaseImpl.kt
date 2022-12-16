package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.repository.RecipeRepository
import javax.inject.Inject

class RefreshAppDataUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository
) : RefreshAppDataUseCase {
    override suspend fun execute(input: Unit) {
        categoryRepository.refreshDatabase()
        productRepository.refreshDatabase()
        recipeRepository.refreshDatabase()
    }
}
