package com.hieuwu.groceriesstore.domain.entities

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithLineItems(
    @Embedded val order:Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId"
    )
    val lineItemList: List<LineItem>
)
