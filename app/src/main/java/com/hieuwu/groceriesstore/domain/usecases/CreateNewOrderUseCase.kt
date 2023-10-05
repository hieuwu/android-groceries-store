package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.data.database.entities.Order

interface CreateNewOrderUseCase :
    SuspendUseCase<CreateNewOrderUseCase.Input, CreateNewOrderUseCase.Output> {
    class Input(val order: Order)

    class Output(result: Unit)
}
