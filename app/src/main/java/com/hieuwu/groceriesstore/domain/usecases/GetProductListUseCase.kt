package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel

interface GetProductListUseCase {
    fun getProductList(): LiveData<List<ProductModel>>
    fun getCurrentCart(): LiveData<OrderModel>?
    suspend fun addToCart(lineItem: LineItem)
    suspend fun createNewOrder(order: Order)
    suspend fun getAllProductsByCategory(categoryId: String):LiveData<List<ProductModel>>
}