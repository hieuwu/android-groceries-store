package com.hieuwu.groceriesstore.domain.mapper

import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface ProductModelToEntity: Mapper<ProductModel, Product> {

}