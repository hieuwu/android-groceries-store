package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : GetProductListUseCase {

    override fun getProductList(): Flow<List<ProductModel>> {
        return productRepository.products
    }

    override fun getCurrentCart(): Flow<OrderModel?> {
        return orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
    }

    override suspend fun addToCart(lineItem: LineItem) {
        orderRepository.addLineItem(lineItem)
    }

    override suspend fun createNewOrder(order: Order) {
        orderRepository.createOrUpdate(order)
    }

    override fun getAllProductsByCategory(categoryId: String) =
        productRepository.getAllProductsByCategory(categoryId)
}
