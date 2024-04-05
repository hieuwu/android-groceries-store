package com.hieuwu.groceriesstore.domain.usecases

interface AddMealToPlanUseCase :
    SuspendUseCase<AddMealToPlanUseCase.Input, AddMealToPlanUseCase.Output> {
    object Input
    object Output
}