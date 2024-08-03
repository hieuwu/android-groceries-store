package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface GetProductsByCategoryUseCase :
    UseCase<GetProductsByCategoryUseCase.Input, GetProductsByCategoryUseCase.Output> {
    class Input(val categoryId: String)
    class Output(val result: Flow<List<ProductModel>>)
}