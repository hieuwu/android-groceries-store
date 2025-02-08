package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.GetOrderListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetOrderListUseCaseImpl(
    private val orderRepository: OrderRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : GetOrderListUseCase {
    override suspend fun invoke(input: GetOrderListUseCase.Input): GetOrderListUseCase.Output {
        return withContext(ioDispatcher) {
            val result = orderRepository.getOrders()
            GetOrderListUseCase.Output.Success(data = result)
        }
    }
}