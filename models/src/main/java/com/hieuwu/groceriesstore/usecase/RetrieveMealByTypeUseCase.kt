package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.MealModel
import com.hieuwu.groceriesstore.models.MealType
import com.hieuwu.groceriesstore.models.WeekDayValue

interface RetrieveMealByTypeUseCase :
    SuspendUseCase<RetrieveMealByTypeUseCase.Input, RetrieveMealByTypeUseCase.Output> {
    class Input(
        val dayValue: WeekDayValue,
        val mealType: MealType,
    )

    sealed class Output {
        class Success(val data: List<MealModel>) : Output()
        data object Failure : Output()
    }
}