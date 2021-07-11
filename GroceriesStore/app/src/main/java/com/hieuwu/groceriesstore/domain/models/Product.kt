package com.hieuwu.groceriesstore.domain.models

data class ProductModel(
    var productId: String? = null,
    var productName: String? = null,
    var productPrice: Double? = null,
    var productSalePrice: String? = null
)