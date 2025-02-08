package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus

class GetCurrentCartUseCaseImpl(
    private val orderRepository: OrderRepository,
) : GetCurrentCartUseCase {

    override suspend fun invoke(input: GetCurrentCartUseCase.Input): GetCurrentCartUseCase.Output {
        val res = orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
        return GetCurrentCartUseCase.Output(res)

    }
}