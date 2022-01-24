package com.hieuwu.groceriesstore.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.hieuwu.groceriesstore.domain.models.LineItemModel


data class ProductAndLineItem(
    @Embedded val lineItem: LineItem?,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productId",
        entity = Product::class
    )
    val product: Product?
)

fun ProductAndLineItem.asDomainModel(): LineItemModel {
    return LineItemModel(
        id = null,
        name = this.product?.name,
        image = this.product?.image,
        productId = this.product?.id,
        quantity = this.lineItem?.quantity,
        subtotal = this.lineItem?.subtotal,
        )
}