package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.LineItemModel
import com.hieuwu.groceriesstore.models.OrderModel
import kotlinx.coroutines.flow.Flow

interface UpdateCartItemUseCase {
    suspend fun updateLineItem(lineItemModel: LineItemModel)
    suspend fun removeLineItem(lineItemModel: LineItemModel)
    fun getCurrentCart(): Flow<OrderModel?>
}
