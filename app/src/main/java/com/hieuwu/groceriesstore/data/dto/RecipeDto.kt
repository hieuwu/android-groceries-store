package com.hieuwu.groceriesstore.data.dto

import com.hieuwu.groceriesstore.data.entities.Recipe
import com.squareup.moshi.Json

data class RecipeDto(
    @Json(name = "id") val id: String,
    @Json(name = "thumbnail_url") val image: String,
    @Json(name = "name") val name: String,
)

fun List<RecipeDto>.asEntity(): List<Recipe> {
    return map {
        Recipe(
            id = it.id,
            name = it.name,
            image = it.image
        )
    }
}