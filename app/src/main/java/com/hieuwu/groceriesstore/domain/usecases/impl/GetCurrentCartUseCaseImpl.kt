package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.OrderRepository
import com.hieuwu.groceriesstore.models.OrderStatus
import com.hieuwu.groceriesstore.usecase.GetCurrentCartUseCase
import javax.inject.Inject

class GetCurrentCartUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
) : GetCurrentCartUseCase {

    override suspend fun invoke(input: GetCurrentCartUseCase.Input): GetCurrentCartUseCase.Output {
        val res = orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
        return GetCurrentCartUseCase.Output(res)

    }
}