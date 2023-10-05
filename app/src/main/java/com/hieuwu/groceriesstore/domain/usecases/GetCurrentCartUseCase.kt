package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.OrderModel
import kotlinx.coroutines.flow.Flow

interface GetCurrentCartUseCase :
    SuspendUseCase<GetCurrentCartUseCase.Input, GetCurrentCartUseCase.Output> {
    class Input
    data class Output(val result: Flow<OrderModel?>)
}
