package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.MealPlanRepository
import com.hieuwu.groceriesstore.domain.usecases.RetrieveMealByTypeUseCase
import javax.inject.Inject

class RetrieveMealByTypeUseCaseImpl @Inject constructor(
    private val mealPlanRepository: MealPlanRepository
) : RetrieveMealByTypeUseCase {
    override suspend fun execute(input: RetrieveMealByTypeUseCase.Input): RetrieveMealByTypeUseCase.Output {
        val result = mealPlanRepository.retrieveMealByType(
            input.mealType.value,
            weekDayValue = input.dayValue.dayValue
        )
        return RetrieveMealByTypeUseCase.Output
    }
}