package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.repository.MealPlanRepository
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class MealPlanRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) : MealPlanRepository {
    override fun addMealToPlan() {
        postgrest["meal_plans"].postgrest["meal_plans"].postgrest["meal_plans"].postgrest["meal_plans"]
    }
}