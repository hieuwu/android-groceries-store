package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface GetProductListUseCase {
    fun getProductList(): Flow<List<ProductModel>>
    fun getCurrentCart(): Flow<OrderModel?>
    suspend fun addToCart(lineItem: LineItem)
    suspend fun createNewOrder(order: Order)
    fun getAllProductsByCategory(categoryId: String): Flow<List<ProductModel>>
}
