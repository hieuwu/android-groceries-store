package com.hieuwu.groceriesstore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDto(

    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("_id")
    val _id: Int,
)
