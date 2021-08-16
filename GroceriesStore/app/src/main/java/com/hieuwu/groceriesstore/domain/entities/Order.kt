package com.hieuwu.groceriesstore.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.data.utils.DataConstant

@Entity(tableName = DataConstant.ORDER_TABLE)
data class Order(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "status")
    var status: String,

    @Embedded val productId: String
)