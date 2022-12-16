package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.OrderModel

interface SubmitOrderUseCase : UseCase<SubmitOrderUseCase.Input, SubmitOrderUseCase.Output> {
    class Input(val order: OrderModel)
    data class Output(val result: Boolean)
}