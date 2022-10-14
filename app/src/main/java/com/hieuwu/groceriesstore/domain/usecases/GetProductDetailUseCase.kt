package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface GetProductDetailUseCase :
    UseCase<GetProductDetailUseCase.Input, GetProductDetailUseCase.Output> {
    class Input(val id: String)
    class Output(result: LiveData<ProductModel>)
}
