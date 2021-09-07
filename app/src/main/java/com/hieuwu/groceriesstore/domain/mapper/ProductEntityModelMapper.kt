package com.hieuwu.groceriesstore.domain.mapper

import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.mapper.entitymapper.EntityModelMapper
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface ProductEntityModelMapper: EntityModelMapper<Product, ProductModel> {
    override fun mapFromEntity(from: Product): ProductModel

    override fun mapToEntity(from: ProductModel): Product
}