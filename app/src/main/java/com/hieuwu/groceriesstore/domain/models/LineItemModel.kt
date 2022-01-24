package com.hieuwu.groceriesstore.domain.models

data class LineItem(
    var id: String,
    var name: String? = null,
    var image: String? = null,
    var productId: String? = null,
    var quantity: Int? = null,
    var subtotal: Int? = null,
)