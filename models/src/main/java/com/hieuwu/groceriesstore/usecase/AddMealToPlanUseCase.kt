package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.MealType


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