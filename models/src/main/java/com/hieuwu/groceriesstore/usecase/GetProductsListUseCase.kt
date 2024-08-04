package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface GetProductsListUseCase: UseCase<GetProductsListUseCase.Input, GetProductsListUseCase.Output> {
    class Input
    class Output(val result: Flow<List<ProductModel>>)
}
