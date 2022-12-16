package com.hieuwu.groceriesstore.data.dto

import com.squareup.moshi.Json

data class RecipeListResponse(
    @Json(name = "count") val count: String,
    @Json(name = "results") var recipesList: MutableList<RecipeDto> = mutableListOf()
)
