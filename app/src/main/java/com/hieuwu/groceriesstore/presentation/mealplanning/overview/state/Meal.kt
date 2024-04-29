package com.hieuwu.groceriesstore.presentation.mealplanning.overview.state

data class Meal(
    val id: String,
    val name: String,
    val imageUrl: String,
    val ingredients: List<String> = listOf(),
)