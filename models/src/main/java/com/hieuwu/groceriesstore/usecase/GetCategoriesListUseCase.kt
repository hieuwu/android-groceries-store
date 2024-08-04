package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.CategoryModel
import kotlinx.coroutines.flow.Flow

interface GetCategoriesListUseCase :
    SuspendUseCase<GetCategoriesListUseCase.Input, GetCategoriesListUseCase.Output> {
    class Input

    class Output(val result: Flow<List<CategoryModel>>)
}
