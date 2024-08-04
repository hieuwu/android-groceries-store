package com.hieuwu.groceriesstore.domain.usecases

interface CreateNewOrderUseCase :
    SuspendUseCase<CreateNewOrderUseCase.Input, CreateNewOrderUseCase.Output> {
    class Input(
        val id: String,
        var status: String,
        var address: String
    )

    class Output(result: Unit)
}
