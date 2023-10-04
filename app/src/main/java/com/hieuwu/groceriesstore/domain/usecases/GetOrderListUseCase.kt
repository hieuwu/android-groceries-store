package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.OrderModel

interface GetOrderListUseCase : SuspendUseCase<GetOrderListUseCase.Input, GetOrderListUseCase.Output> {
    class Input
    sealed class Output {
        class Success(val data: List<OrderModel>) : Output()
        object Failure : Output()
    }
}
