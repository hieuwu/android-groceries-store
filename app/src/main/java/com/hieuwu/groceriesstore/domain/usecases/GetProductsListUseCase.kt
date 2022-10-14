package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface GetProductsListUseCase: UseCase<GetProductsListUseCase.Input, GetProductsListUseCase.Output> {
    class Input
    class Output(result: Flow<List<ProductModel>>)
}