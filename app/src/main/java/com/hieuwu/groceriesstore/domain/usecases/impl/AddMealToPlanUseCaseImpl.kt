package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.usecases.AddMealToPlanUseCase
import javax.inject.Inject

class AddMealToPlanUseCaseImpl @Inject constructor() : AddMealToPlanUseCase {
    override suspend fun execute(input: AddMealToPlanUseCase.Input): AddMealToPlanUseCase.Output {

        return AddMealToPlanUseCase.Output
    }
}