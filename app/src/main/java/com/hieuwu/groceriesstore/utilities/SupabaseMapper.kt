package com.hieuwu.groceriesstore.utilities

import com.hieuwu.groceriesstore.data.database.entities.Category
import com.hieuwu.groceriesstore.data.database.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.data.database.entities.Product
import com.hieuwu.groceriesstore.data.database.entities.User
import com.hieuwu.groceriesstore.data.network.dto.CategoriesDto
import com.hieuwu.groceriesstore.data.network.dto.LineItemDto
import com.hieuwu.groceriesstore.data.network.dto.OrderDto
import com.hieuwu.groceriesstore.data.network.dto.ProductDto
import com.hieuwu.groceriesstore.data.network.dto.UserDto
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel

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


    fun mapDtoToEntity(user: UserDto): User {
        return User(
            id = user.id,
            name = user.name,
            email = user.email,
            address = user.address,
            phone = user.phone,
            isDataRefreshedNotiEnabled = user.isDataRefreshedNotiEnabled,
            isOrderCreatedNotiEnabled = user.isOrderCreatedNotiEnabled,
            isPromotionNotiEnabled = user.isPromotionNotiEnabled,
        )
    }

    fun mapEntityToDto(user: User): UserDto {
        return UserDto(
            id = user.id,
            name = user.name,
            email = user.email,
            address = user.address,
            phone = user.phone,
            isDataRefreshedNotiEnabled = user.isDataRefreshedNotiEnabled,
            isOrderCreatedNotiEnabled = user.isOrderCreatedNotiEnabled,
            isPromotionNotiEnabled = user.isPromotionNotiEnabled,
        )
    }

    fun mapModelToDto(order: OrderModel): OrderDto {
        return OrderDto(
            id = order.id,
            orderId = order.id,
            address = order.address,
            status = order.status ?: "",
        )
    }

    fun mapModelToDto(lineItemModel: LineItemModel): LineItemDto {
        return LineItemDto(
            id = lineItemModel.id ?: 0,
            productId = lineItemModel.productId ?: "",
            orderId = "",
            quantity = lineItemModel.quantity ?: 0,
            subtotal = lineItemModel.subtotal ?: 0.0,
            lineItemId = lineItemModel.id ?: 0
        )
    }
}