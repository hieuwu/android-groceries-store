package com.hieuwu.groceriesstore.domain.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.data.utils.DataConstant

@Entity(tableName = DataConstant.CATEGORY_TABLE)
data class Category(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "image")
    val image: String?,
)