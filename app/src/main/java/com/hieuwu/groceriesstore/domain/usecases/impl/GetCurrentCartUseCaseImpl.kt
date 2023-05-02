package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentCartUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository,
) : GetCurrentCartUseCase {

    override suspend fun execute(input: GetCurrentCartUseCase.Input): GetCurrentCartUseCase.Output {
        return withContext(Dispatchers.IO) {
            val res = orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
            GetCurrentCartUseCase.Output(res)
        }
    }
}