package com.hieuwu.groceriesstore.utilities

import com.hieuwu.groceriesstore.data.database.entities.Category
import com.hieuwu.groceriesstore.data.network.dto.CategoriesDto

object SupabaseMapper {
    fun mapToEntity(category: CategoriesDto): Category {
        return Category(
            id = category.id,
            name = category.name,
            image = category.image
        )
    }
}