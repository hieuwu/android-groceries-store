package com.hieuwu.groceriesstore.data.database.entities

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
        id = this.lineItem?.id,
        name = this.product?.name,
        image = this.product?.image,
        price = this.product?.price,
        productId = this.product?.id,
        quantity = this.lineItem?.quantity
    )
}

fun MutableList<ProductAndLineItem>.asDomainModel(): MutableList<LineItemModel> {
    return map {
        it.asDomainModel()
    } as MutableList<LineItemModel>
}
