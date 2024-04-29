package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.MealModel
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.MealType
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.WeekDayValue

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