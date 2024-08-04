package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.OrderModel

interface GetOrderListUseCase : SuspendUseCase<GetOrderListUseCase.Input, GetOrderListUseCase.Output> {
    class Input
    sealed class Output {
        class Success(val data: List<OrderModel>) : Output()
        object Failure : Output()
    }
}
