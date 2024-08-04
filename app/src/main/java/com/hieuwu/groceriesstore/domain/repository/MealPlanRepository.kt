package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.models.MealModel

interface MealPlanRepository {
    suspend fun addMealToPlan(
        weekDay: String,
        name: String,
        ingredients: List<String>,
        mealType: String,
        mealImageUri: ByteArray
    )

    suspend fun retrieveMealByType(type: String, weekDayValue: String): List<MealModel>
    suspend fun removeMealFromPlan(id: String)
}