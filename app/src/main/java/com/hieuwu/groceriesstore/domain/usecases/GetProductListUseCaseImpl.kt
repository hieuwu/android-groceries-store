package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.utilities.OrderStatus
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : GetProductListUseCase {

    override fun getProductList(): LiveData<List<ProductModel>> {
        return productRepository.products
    }

    override fun getCurrentCart(): LiveData<OrderModel>? {
        return orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
    }

    override suspend fun addToCart(lineItem: LineItem) {
        orderRepository.addLineItem(lineItem)
    }

    override suspend fun createNewOrder(order: Order) {
        orderRepository.createOrUpdate(order)
    }

    override suspend fun getAllProductsByCategory(categoryId: String) =
        productRepository.getAllProductsByCategory(categoryId)
}
