package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.MealPlanRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.domain.models.MealModel
import com.hieuwu.groceriesstore.domain.usecases.RetrieveMealByTypeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrieveMealByTypeUseCaseImpl @Inject constructor(
    private val mealPlanRepository: MealPlanRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : RetrieveMealByTypeUseCase {
    override suspend fun execute(input: RetrieveMealByTypeUseCase.Input): RetrieveMealByTypeUseCase.Output {
        return try {
            withContext(ioDispatcher) {
                val result = flow<List<MealModel>> {
                    mealPlanRepository.retrieveMealByType(
                        type = input.mealType.value,
                        weekDayValue = input.dayValue.dayValue
                    )
                }
                return@withContext RetrieveMealByTypeUseCase.Output.Success(data = result)
            }
        } catch (e: Exception) {
            return RetrieveMealByTypeUseCase.Output.Failure
        }
    }
}