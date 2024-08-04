package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.OrderRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.usecase.GetOrderListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetOrderListUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : GetOrderListUseCase {
    override suspend fun invoke(input: GetOrderListUseCase.Input): GetOrderListUseCase.Output {
        return withContext(ioDispatcher) {
            val result = orderRepository.getOrders()
            GetOrderListUseCase.Output.Success(data = result)
        }
    }
}