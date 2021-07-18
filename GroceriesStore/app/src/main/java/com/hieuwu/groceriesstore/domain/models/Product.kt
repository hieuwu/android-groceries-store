package com.hieuwu.groceriesstore.domain.models

data class ProductModel(
    var productId: String,
    var productName: String? = null,
    var productPrice: Double? = null,
    var productSalePrice: String? = null
)