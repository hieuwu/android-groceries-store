package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.SubmitOrderUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubmitOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
) : SubmitOrderUseCase {
    override suspend fun execute(input: SubmitOrderUseCase.Input): SubmitOrderUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = orderRepository.sendOrderToServer(input.order)
            SubmitOrderUseCase.Output(result)
        }
    }
}