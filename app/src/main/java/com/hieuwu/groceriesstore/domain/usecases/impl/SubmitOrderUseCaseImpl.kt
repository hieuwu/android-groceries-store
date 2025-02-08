package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.SubmitOrderUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SubmitOrderUseCaseImpl (
    private val orderRepository: OrderRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : SubmitOrderUseCase {
    override suspend fun invoke(input: SubmitOrderUseCase.Input): SubmitOrderUseCase.Output {
        return withContext(ioDispatcher) {
            val result = orderRepository.sendOrderToServer(input.order)
            SubmitOrderUseCase.Output(result)
        }
    }
}