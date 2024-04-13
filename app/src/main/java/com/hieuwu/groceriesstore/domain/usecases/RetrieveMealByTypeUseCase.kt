package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.presentation.mealplanning.overview.MealType
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.WeekDayValue

interface RetrieveMealByTypeUseCase :
    SuspendUseCase<RetrieveMealByTypeUseCase.Input, RetrieveMealByTypeUseCase.Output> {
    class Input(
        val dayValue: WeekDayValue,
        val mealType: MealType,
    )

    object Output
}