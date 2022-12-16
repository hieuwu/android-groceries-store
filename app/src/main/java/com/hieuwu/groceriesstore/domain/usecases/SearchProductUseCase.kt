package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface SearchProductUseCase : UseCase<SearchProductUseCase.Input, SearchProductUseCase.Output> {
    class Input(val name: String? = null)
    class Output(val result: LiveData<List<ProductModel>>)
}