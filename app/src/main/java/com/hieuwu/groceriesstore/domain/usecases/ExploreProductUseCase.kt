package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow

interface ExploreProductUseCase {
    fun getCategoryList(): LiveData<List<CategoryModel>>
    fun searchProductByName(name: String?): LiveData<List<ProductModel>>
    fun getCurrentCart(): Flow<OrderModel?>
    suspend fun addToCart(lineItem: LineItem)
    suspend fun createNewOrder(order: Order)
}
