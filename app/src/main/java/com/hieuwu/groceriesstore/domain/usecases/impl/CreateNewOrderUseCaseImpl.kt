package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateNewOrderUseCaseImpl @Inject constructor(private val orderRepository: OrderRepository) :
    CreateNewOrderUseCase {
    override suspend fun execute(input: CreateNewOrderUseCase.Input): CreateNewOrderUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = orderRepository.createOrUpdate(input.order)
            CreateNewOrderUseCase.Output(result)
        }
    }
}