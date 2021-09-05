package com.hieuwu.groceriesstore.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class ProductAndLineItem(
    @Embedded val lineItem: LineItem?,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val product: Product?
)