package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface SearchProductUseCase : SuspendUseCase<SearchProductUseCase.Input, SearchProductUseCase.Output> {
    class Input(val name: String? = null)
    class Output(val result: Flow<List<ProductModel>>)
}
