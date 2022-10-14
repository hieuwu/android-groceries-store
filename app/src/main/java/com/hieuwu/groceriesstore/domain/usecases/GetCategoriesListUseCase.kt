package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.CategoryModel

interface GetCategoriesListUseCase :
    UseCase<GetCategoriesListUseCase.Input, GetCategoriesListUseCase.Output> {
    class Input

    class Output(val result: LiveData<List<CategoryModel>>)
}