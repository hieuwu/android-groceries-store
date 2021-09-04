package com.hieuwu.groceriesstore.domain.entities

import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant

@Entity(
    tableName = DataConstant.LINE_ITEM_TABLE
//    , foreignKeys = [
//        ForeignKey(
//            entity = Order::class,
//            parentColumns = arrayOf("lineItemId"),
//            childColumns = arrayOf("orderId"),
//            onDelete = ForeignKey.CASCADE
//        )], indices = [
//        Index(value = arrayOf("orderId")),
//        Index(value = arrayOf("lineItemId"), unique = true)
//    ]
)
data class LineItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lineItemId")
    val id: Long,

    @ColumnInfo(name = "productId")
    val productId: String,

    @ColumnInfo(name = "orderId")
    val orderId: String,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "subtotal")
    var subtotal: Double
) {

    constructor(productId: String, orderId: String, quantity: Int, subtotal: Double) : this(
        0,
        productId,
        orderId,
        quantity,
        subtotal
    )

}