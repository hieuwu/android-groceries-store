package com.hieuwu.groceriesstore.utilities

import com.hieuwu.groceriesstore.data.database.entities.Category
import com.hieuwu.groceriesstore.data.database.entities.Product
import com.hieuwu.groceriesstore.data.network.dto.CategoriesDto
import com.hieuwu.groceriesstore.data.network.dto.ProductDto

object SupabaseMapper {
    fun mapToEntity(category: CategoriesDto): Category {
        return Category(
            id = category.id,
            name = category.name,
            image = category.image
        )
    }

    fun mapToEntity(productDto: ProductDto): Product {
        return Product(
            id = productDto.productId,
            name = productDto.name,
            nutrition = productDto.nutrition,
            description = productDto.description,
            image = productDto.image,
            price = productDto.price,
            category = productDto.category,
            )
    }
}