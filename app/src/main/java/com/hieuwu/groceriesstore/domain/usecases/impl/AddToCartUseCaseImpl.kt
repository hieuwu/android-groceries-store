package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddToCartUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    AddToCartUseCase {
    override suspend fun invoke(input: AddToCartUseCase.Input): AddToCartUseCase.Output {
        withContext(ioDispatcher) {
            orderRepository.addLineItem(input.lineItem)
        }
        return AddToCartUseCase.Output()
    }
}