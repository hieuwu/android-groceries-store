package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import javax.inject.Inject

class SubmitOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
) : SubmitOrderUseCase {
    override suspend fun execute(input: SubmitOrderUseCase.Input): SubmitOrderUseCase.Output {
        val result = orderRepository.sendOrderToServer(input.order)
        return SubmitOrderUseCase.Output(result)
    }
}