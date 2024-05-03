package com.hieuwu.groceriesstore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("ingredients")
    val ingredients: List<String>,

    @SerialName("week_day")
    val weekDay: String,

    @SerialName("creator")
    val creatorId: String,

    @SerialName("meal_type")
    val mealType: String,

    @SerialName("image")
    val image: String?,

)
