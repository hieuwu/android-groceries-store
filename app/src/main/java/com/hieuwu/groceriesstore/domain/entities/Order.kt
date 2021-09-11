package com.hieuwu.groceriesstore.domain.entities

import androidx.room.*
import com.hieuwu.groceriesstore.data.utils.DataConstant
import com.hieuwu.groceriesstore.data.utils.OrderStatus

@Entity(tableName = DataConstant.ORDER_TABLE)
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "orderId")
    val id: String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "delivery")
    var delivery: String?
)