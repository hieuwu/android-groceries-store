package com.hieuwu.groceriesstore.domain.models

data class OrderModel(
    var status: String? = null,
    var address: String? = null,
    val lineItemList: MutableList<LineItemModel>
)