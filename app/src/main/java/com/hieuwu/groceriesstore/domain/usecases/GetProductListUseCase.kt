package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

@Deprecated("Use use case")
interface GetProductListUseCase {
    @Deprecated("Use GetProductsListUseCase")
    fun getProductList(): Flow<List<ProductModel>>

    @Deprecated("Use GetCurrentCartUseCase")
    fun getCurrentCart(): Flow<OrderModel?>

    @Deprecated("Use AddToCardUseCase")
    suspend fun addToCart(lineItem: LineItem)

    @Deprecated("Use CreateNewOrderUseCase")
    suspend fun createNewOrder(order: Order)

    @Deprecated("Use GetProductsByCategoryUseCase")
    fun getAllProductsByCategory(categoryId: String): Flow<List<ProductModel>>
}
