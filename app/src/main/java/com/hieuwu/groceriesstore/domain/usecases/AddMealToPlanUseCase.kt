package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.MealType

interface AddMealToPlanUseCase :
    SuspendUseCase<AddMealToPlanUseCase.Input, AddMealToPlanUseCase.Output> {
    class Input(
        val name: String,
        val weekDay: String,
        val ingredients: List<String>,
        val mealType: MealType,
        val mealImageUri: ByteArray,
    )

    object Output
}