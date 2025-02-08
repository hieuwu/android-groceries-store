package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.MealPlanRepository
import com.hieuwu.groceriesstore.domain.usecases.RetrieveMealByTypeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RetrieveMealByTypeUseCaseImpl (
    private val mealPlanRepository: MealPlanRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : RetrieveMealByTypeUseCase {
    override suspend fun invoke(input: RetrieveMealByTypeUseCase.Input): RetrieveMealByTypeUseCase.Output {
        return withContext(ioDispatcher) {
            try {
                val result = mealPlanRepository.retrieveMealByType(
                    type = input.mealType.value,
                    weekDayValue = input.dayValue.dayValue
                )
                RetrieveMealByTypeUseCase.Output.Success(data = result)
            } catch (e: Exception) {
                RetrieveMealByTypeUseCase.Output.Failure
            }
        }
    }
}