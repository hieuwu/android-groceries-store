package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesListUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesListUseCase {
    override suspend fun execute(input: GetCategoriesListUseCase.Input): GetCategoriesListUseCase.Output {
        val result = categoryRepository.getFromLocal()
        return GetCategoriesListUseCase.Output(result)
    }
}