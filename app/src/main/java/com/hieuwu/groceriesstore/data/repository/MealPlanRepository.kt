package com.hieuwu.groceriesstore.data.repository

interface MealPlanRepository {
    suspend fun addMealToPlan(name: String, ingredients: List<String>)
}