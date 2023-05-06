package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.OrderModel
import kotlinx.coroutines.flow.Flow

interface GetOrderListUseCase: UseCase<GetOrderListUseCase.Input, GetOrderListUseCase.Output> {
    class Input
    class Output(val data: Flow<List<OrderModel>>)
}