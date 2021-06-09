package com.hieuwu.groceriesstore.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.data.utils.DataConstant

@Entity(tableName=DataConstant.PRODUCT_TABLE)
data class Product(
    @PrimaryKey
    val id:String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "price")
    var price: Double
)