package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface GetCategoriesListUseCase :
    UseCase<GetCategoriesListUseCase.Input, GetCategoriesListUseCase.Output> {
    class Input

    class Output(val result: Flow<List<CategoryModel>>)
}