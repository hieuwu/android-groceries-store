package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CreateNewOrderUseCaseImpl(
    private val orderRepository: OrderRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : CreateNewOrderUseCase {
    override suspend fun invoke(input: CreateNewOrderUseCase.Input): CreateNewOrderUseCase.Output {
        return withContext(ioDispatcher) {
            val result = orderRepository.createOrUpdate(input.order)
            CreateNewOrderUseCase.Output(result)
        }
    }
}