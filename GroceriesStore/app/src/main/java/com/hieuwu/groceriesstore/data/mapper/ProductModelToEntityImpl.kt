package com.hieuwu.groceriesstore.data.mapper

import android.content.Context
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.ProductModelToEntity
import com.hieuwu.groceriesstore.domain.models.ProductModel
import javax.inject.Inject

class ProductModelToEntityImpl @Inject constructor(private val context: Context) :
    ProductModelToEntity {
    override fun map(from: ProductModel): Product {
        return Product(from.productId, from.productName, from.productName, from.productPrice)
    }
}