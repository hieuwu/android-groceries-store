package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExploreProductProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val categoryRepository: CategoryRepository
) : ExploreProductUseCase {
    override fun getCategoryList(): LiveData<List<CategoryModel>> =
        categoryRepository.getFromLocal()

    override fun searchProductByName(name: String?): LiveData<List<ProductModel>> =
        productRepository.searchProductsListByName(name)

    override fun getCurrentCart(): Flow<OrderModel?> {
        return orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
    }

    override suspend fun addToCart(lineItem: LineItem) {
        orderRepository.addLineItem(lineItem)
    }

    override suspend fun createNewOrder(order: Order) {
        orderRepository.createOrUpdate(order)
    }
}
