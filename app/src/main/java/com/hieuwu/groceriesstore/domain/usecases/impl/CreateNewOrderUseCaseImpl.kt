package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateNewOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    CreateNewOrderUseCase {
    override suspend fun invoke(input: CreateNewOrderUseCase.Input): CreateNewOrderUseCase.Output {
        return withContext(ioDispatcher) {
            val result = orderRepository.createOrUpdate(input.order)
            CreateNewOrderUseCase.Output(result)
        }
    }
}