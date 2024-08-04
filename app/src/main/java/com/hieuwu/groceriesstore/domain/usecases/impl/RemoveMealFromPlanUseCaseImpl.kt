package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.MealPlanRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.usecase.RemoveMealFromPlanUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveMealFromPlanUseCaseImpl @Inject constructor(
    private val mealPlanRepository: MealPlanRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : RemoveMealFromPlanUseCase {
    override suspend fun invoke(input: RemoveMealFromPlanUseCase.Input): RemoveMealFromPlanUseCase.Output {
        return withContext(ioDispatcher) {
            try {
                mealPlanRepository.removeMealFromPlan(input.id)
                RemoveMealFromPlanUseCase.Output.Success
            } catch (e: Exception) {
                RemoveMealFromPlanUseCase.Output.Failure
            }
        }
    }
}