package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.GetOrderListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetOrderListUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : GetOrderListUseCase {
    override suspend fun execute(input: GetOrderListUseCase.Input): GetOrderListUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = orderRepository.getOrders()
            GetOrderListUseCase.Output.Success(data = result)
        }
    }
}