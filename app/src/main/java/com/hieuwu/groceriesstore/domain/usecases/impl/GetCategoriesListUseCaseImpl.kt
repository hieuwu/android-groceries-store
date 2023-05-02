package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.usecases.GetCategoriesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCategoriesListUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesListUseCase {
    override suspend fun execute(input: GetCategoriesListUseCase.Input): GetCategoriesListUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = categoryRepository.getFromLocal()
            GetCategoriesListUseCase.Output(result)
        }
    }
}