package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
class AddToCartUseCaseImpl(
    private val orderRepository: OrderRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : AddToCartUseCase {
    override suspend fun invoke(input: AddToCartUseCase.Input): AddToCartUseCase.Output {
        withContext(ioDispatcher) {
            orderRepository.addLineItem(input.lineItem)
        }
        return AddToCartUseCase.Output()
    }
}