package com.hieuwu.groceriesstore.domain.entities

import androidx.room.Embedded
import androidx.room.Relation


data class ProductAndLineItem(
    @Embedded val lineItem: LineItem?,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productId",
        entity = Product::class
    )
    val product: Product?
)