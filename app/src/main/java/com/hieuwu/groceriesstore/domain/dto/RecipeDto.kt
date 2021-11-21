package com.hieuwu.groceriesstore.domain.dto

import com.squareup.moshi.Json

data class Recipe(
    @Json(name = "id") val id: String,
    @Json(name = "thumbnail_url") val image: String,
    @Json(name = "name") val name: String,
)