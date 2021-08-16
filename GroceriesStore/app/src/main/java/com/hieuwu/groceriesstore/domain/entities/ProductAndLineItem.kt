package com.hieuwu.groceriesstore.domain.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ProductAndLineItem(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "userId"
    )
    val lineItem: LineItem
)