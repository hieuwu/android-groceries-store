package com.hieuwu.groceriesstore.domain.entities

import androidx.room.*
import com.hieuwu.groceriesstore.utilities.ORDER_TABLE

@Entity(tableName = ORDER_TABLE)
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "orderId")
    val id: String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "address")
    var address: String
)