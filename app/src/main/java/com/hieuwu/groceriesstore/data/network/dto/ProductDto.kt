package com.hieuwu.groceriesstore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id")
    val productId: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Double,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String,
    @SerialName("nutrition")
    val nutrition: String,
)
