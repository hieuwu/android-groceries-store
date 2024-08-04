package com.hieuwu.groceriesstore.models

data class ProductModel(
    var id: String,
    var name: String? = null,
    var price: Double? = null,
    var image: String? = null,
    var description: String? = null,
    var nutrition: String? = null
)
