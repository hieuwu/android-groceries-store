package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import javax.inject.Inject

class AddToCartUseCaseImpl @Inject constructor(private val orderRepository: OrderRepository) :
    AddToCartUseCase {
    override suspend fun execute(input: AddToCartUseCase.Input): AddToCartUseCase.Output {
        orderRepository.addLineItem(input.lineItem)
        return AddToCartUseCase.Output()
    }
}