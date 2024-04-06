package com.hieuwu.groceriesstore.domain.usecases

interface AddMealToPlanUseCase :
    SuspendUseCase<AddMealToPlanUseCase.Input, AddMealToPlanUseCase.Output> {
    class Input(val name: String, val ingredients: List<String>)
    object Output
}