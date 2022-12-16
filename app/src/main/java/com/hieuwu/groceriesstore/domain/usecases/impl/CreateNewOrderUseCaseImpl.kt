package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import javax.inject.Inject

class CreateNewOrderUseCaseImpl @Inject constructor(private val orderRepository: OrderRepository) :
    CreateNewOrderUseCase {
    override suspend fun execute(input: CreateNewOrderUseCase.Input): CreateNewOrderUseCase.Output {
        val result = orderRepository.createOrUpdate(input.order)
        return CreateNewOrderUseCase.Output(result)
    }
}