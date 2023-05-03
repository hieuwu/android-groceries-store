package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddToCartUseCaseImpl @Inject constructor(private val orderRepository: OrderRepository) :
    AddToCartUseCase {
    override suspend fun execute(input: AddToCartUseCase.Input): AddToCartUseCase.Output {
        withContext(Dispatchers.IO) {
            orderRepository.addLineItem(input.lineItem)
        }
        return AddToCartUseCase.Output()
    }
}