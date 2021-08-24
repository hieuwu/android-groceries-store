package com.hieuwu.groceriesstore.domain.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.data.utils.DataConstant

@Entity(tableName = DataConstant.LINE_ITEM_TABLE)
data class LineItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lineItemId")
    val id: Long,

    @ColumnInfo(name = "productId")
    val productId: String,

    @ColumnInfo(name = "orderId")
    val orderId: String,

    val quantity: Int,
    val subtotal: Double
) {

    constructor(productId: String, orderId: String, quantity: Int, subtotal: Double) : this(
        0,
        productId,
        orderId,
        quantity,
        subtotal
    )

}