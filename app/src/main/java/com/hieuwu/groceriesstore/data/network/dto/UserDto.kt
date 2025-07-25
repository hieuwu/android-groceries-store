package com.hieuwu.groceriesstore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("email")
    val email: String,

    @SerialName("address")
    val address: String?,

    @SerialName("phone")
    val phone: String?,

    @SerialName("isordercreatednotienabled")
    val isOrderCreatedNotiEnabled: Boolean,

    @SerialName("ispromotionnotienabled")
    val isPromotionNotiEnabled: Boolean,

    @SerialName("isdatarefreshednotienabled")
    val isDataRefreshedNotiEnabled: Boolean,

)
