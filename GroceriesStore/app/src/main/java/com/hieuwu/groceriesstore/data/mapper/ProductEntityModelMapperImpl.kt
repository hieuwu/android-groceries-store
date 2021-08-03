package com.hieuwu.groceriesstore.data.mapper

import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.ProductEntityModelMapper
import com.hieuwu.groceriesstore.domain.models.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductEntityModelMapperImpl @Inject constructor(): ProductEntityModelMapper {
    override fun mapFromEntity(from: Product): ProductModel {
        return ProductModel(from.id, from.name,from.price,"0")
    }

    override fun mapToEntity(from: ProductModel): Product {
        return Product(from.productId!!, from.productName, from.productName, from.productPrice)
    }
}