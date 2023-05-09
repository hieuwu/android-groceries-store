package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.GetOrderListUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOrderListUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : GetOrderListUseCase {
    override suspend fun execute(input: GetOrderListUseCase.Input): GetOrderListUseCase.Output {
        val result = orderRepository.getOrders()
        return GetOrderListUseCase.Output.Success(data = flow { result })
    }
}