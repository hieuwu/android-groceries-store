package com.hieuwu.groceriesstore.domain.models

data class MealModel(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val weekDay: String,
    val creatorId: String,
    val mealType: String,
    val imageUrl: String,
)
