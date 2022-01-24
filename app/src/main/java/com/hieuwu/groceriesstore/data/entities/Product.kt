package com.hieuwu.groceriesstore.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.utilities.PRODUCT_TABLE

@Entity(tableName = PRODUCT_TABLE)
data class Product(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "productId")
    val id: String,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "price")
    var price: Double?,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "category")
    var category: String?,

    @ColumnInfo(name = "nutrition")
    var nutrition: String?,
)

fun List<Product>.asDomainModel(): List<ProductModel> {
    return map {
        ProductModel(
            id = it.id,
            name = it.name,
            description = it.description,
            price = it.price,
            image = it.image,
            nutrition = it.nutrition
        )
    }
}