package com.hieuwu.groceriesstore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    @SerialName("orderId")
    val orderId: String,
    @SerialName("status")
    val status: String,
    @SerialName("address")
    val address: String?,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("total")
    val total: Double,
)
