package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.CategoryRepository
import com.hieuwu.groceriesstore.usecase.GetCategoriesListUseCase
import javax.inject.Inject

class GetCategoriesListUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesListUseCase {
    override suspend fun invoke(input: GetCategoriesListUseCase.Input): GetCategoriesListUseCase.Output {
        val result = categoryRepository.getFromLocal()
        return GetCategoriesListUseCase.Output(result)
    }
}