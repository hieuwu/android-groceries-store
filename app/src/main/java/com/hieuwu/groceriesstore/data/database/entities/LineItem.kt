package com.hieuwu.groceriesstore.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.utilities.LINE_ITEM_TABLE

@Entity(tableName = LINE_ITEM_TABLE)
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
