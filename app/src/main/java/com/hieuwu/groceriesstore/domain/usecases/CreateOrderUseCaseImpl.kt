package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.utilities.OrderStatus
import javax.inject.Inject

class CreateOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository
) : CreateOrderUseCase {

    override suspend fun sendOrderToServer(order: OrderModel) = orderRepository.sendOrderToServer(order)

    override fun getCurrentUser() = userRepository.getCurrentUser()

    override fun setOrderAddress() {
        TODO("Not yet implemented")
    }

    override fun getCurrentCart() = orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
}