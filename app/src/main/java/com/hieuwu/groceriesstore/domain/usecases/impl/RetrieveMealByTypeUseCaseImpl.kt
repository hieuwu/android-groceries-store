package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.MealPlanRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.usecase.RetrieveMealByTypeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrieveMealByTypeUseCaseImpl @Inject constructor(
    private val mealPlanRepository: MealPlanRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
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