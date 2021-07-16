package com.hieuwu.groceriesstore.data.mapper

import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.ProductModelToEntity
import com.hieuwu.groceriesstore.domain.models.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductModelToEntityImpl @Inject constructor() : ProductModelToEntity {

    override fun map(from: ProductModel): Product {
        return Product(from.productId, from.productName, from.productName, from.productPrice)
    }
}