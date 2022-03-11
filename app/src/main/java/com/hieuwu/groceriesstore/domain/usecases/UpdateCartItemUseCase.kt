package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel

interface UpdateCartItemUseCase {
    suspend fun updateLineItem(lineItemModel: LineItemModel)
    suspend fun removeLineItem(lineItemModel: LineItemModel)
    fun getCurrentCart(): LiveData<OrderModel>?
}