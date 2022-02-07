package com.hieuwu.groceriesstore.domain.models

data class LineItemModel(
    var id: String? = null,
    var name: String? = null,
    var price: Double? = null,
    var image: String? = null,
    var productId: String? = null,
    var quantity: Int? = null,
    var subtotal: Double? = null,
)