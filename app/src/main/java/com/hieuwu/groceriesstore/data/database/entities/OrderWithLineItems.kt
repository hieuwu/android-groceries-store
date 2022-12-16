package com.hieuwu.groceriesstore.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.hieuwu.groceriesstore.domain.models.OrderModel

data class OrderWithLineItems(
    @Embedded var order: Order,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId",
        entity = LineItem::class
    )
    val lineItemList: MutableList<ProductAndLineItem>
)

fun OrderWithLineItems.asDomainModel(): OrderModel {
    return OrderModel(
        id = this.order.id,
        status = this.order.status,
        address = this.order.address,
        lineItemList = this.lineItemList.asDomainModel()
    )
}
