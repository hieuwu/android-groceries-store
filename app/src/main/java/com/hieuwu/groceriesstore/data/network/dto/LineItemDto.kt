package com.hieuwu.groceriesstore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LineItemDto(

    @SerialName("id")
    val id: Long,

    @SerialName("lineItemId")
    val lineItemId: Long,

    @SerialName("productid")
    val productId: String,

    @SerialName("orderId")
    val orderId: String,

    @SerialName("quantity")
    var quantity: Int,

    @SerialName("subtotal")
    var subtotal: Double

)
