package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow
@Deprecated("Use Use case")
interface ExploreProductUseCase {
    @Deprecated("Use GetCategoriesListUseCase")
    fun getCategoryList(): LiveData<List<CategoryModel>>

    @Deprecated("Use SearchProductUseCase")
    fun searchProductByName(name: String?): LiveData<List<ProductModel>>

    @Deprecated("Use GetCurrentCartUseCase")
    fun getCurrentCart(): Flow<OrderModel?>

    @Deprecated("Use AddToCartUseCase")
    suspend fun addToCart(lineItem: LineItem)

    @Deprecated("Use CreateNewOrderUseCase")
    suspend fun createNewOrder(order: Order)
}