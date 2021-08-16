package com.hieuwu.groceriesstore.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.data.utils.DataConstant

@Entity(tableName = DataConstant.LINE_ITEM_TABLE)
data class LineItem(
    @PrimaryKey
    @ColumnInfo(name = "lineItemId")
    val id: Long,
    val productId: String
)