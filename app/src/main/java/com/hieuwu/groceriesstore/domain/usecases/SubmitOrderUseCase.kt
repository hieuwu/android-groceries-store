package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.models.OrderModel

interface SubmitOrderUseCase : SuspendUseCase<SubmitOrderUseCase.Input, SubmitOrderUseCase.Output> {
    class Input(val order: OrderModel)
    data class Output(val result: Boolean)
}
