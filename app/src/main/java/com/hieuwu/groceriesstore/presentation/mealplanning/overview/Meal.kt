package com.hieuwu.groceriesstore.presentation.mealplanning.overview

data class Meal(
    val name: String,
    val imageUrl: String,
    val ingredients: List<String> = listOf(),
)