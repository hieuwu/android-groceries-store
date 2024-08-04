package com.hieuwu.groceriesstore.models

data class UserModel(
    val id: String,
    val name: String? = null,
    val email: String?,
    val phone: String,
    val address: String,
    var isOrderCreatedNotiEnabled: Boolean,
    var isPromotionNotiEnabled: Boolean,
    var isDataRefreshedNotiEnabled: Boolean
)