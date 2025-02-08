package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.usecases.GetCategoriesListUseCase

class GetCategoriesListUseCaseImpl(
    private val categoryRepository: CategoryRepository
) : GetCategoriesListUseCase {
    override suspend fun invoke(input: GetCategoriesListUseCase.Input): GetCategoriesListUseCase.Output {
        val result = categoryRepository.getFromLocal()
        return GetCategoriesListUseCase.Output(result)
    }
}