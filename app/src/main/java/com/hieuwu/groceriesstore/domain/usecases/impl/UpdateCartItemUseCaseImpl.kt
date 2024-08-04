package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.OrderRepository
import com.hieuwu.groceriesstore.repository.ProductRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.models.LineItemModel
import com.hieuwu.groceriesstore.models.OrderModel
import com.hieuwu.groceriesstore.models.OrderStatus
import com.hieuwu.groceriesstore.usecase.UpdateCartItemUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateCartItemUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UpdateCartItemUseCase {
    override suspend fun updateLineItem(lineItemModel: LineItemModel) {
        withContext(ioDispatcher) {
            if (lineItemModel.quantity != null && lineItemModel.id != null) {
                productRepository.updateLineItemQuantityById(
                    lineItemModel.quantity!!,
                    lineItemModel.id!!
                )
            }
        }
    }

    override suspend fun removeLineItem(lineItemModel: LineItemModel) {
        withContext(ioDispatcher) {
            if (lineItemModel.id != null) {
                productRepository.removeLineItemById(lineItemModel.id!!)
            }
        }
    }

    override fun getCurrentCart(): Flow<OrderModel?> {
        return orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
    }
}
