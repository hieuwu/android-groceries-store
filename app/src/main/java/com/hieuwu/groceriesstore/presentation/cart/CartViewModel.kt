package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : ObservableViewModel() {

    private var _order: MutableLiveData<OrderModel> =
        orderRepository.getOneOrderByStatus(OrderStatus.IN_CART) as MutableLiveData<OrderModel>
    val order: LiveData<OrderModel>
        get() = _order

    fun decreaseQty(lineItemModel: LineItemModel) {
        Timber.d("Minus Clicked")
        lineItemModel.decreaseQuantity()
        viewModelScope.launch {
            updateLineItem(lineItemModel)
        }
    }

    fun increaseQty(lineItemModel: LineItemModel) {
        Timber.d("Plus Clicked")
        lineItemModel.increaseQuantity()
        viewModelScope.launch {
            updateLineItem(lineItemModel)
        }
    }

    fun removeItem(lineItemModel: LineItemModel) {
        lineItemModel.decreaseQuantity()
        viewModelScope.launch {
            removeLineItem(lineItemModel)
        }
    }

    private suspend fun updateLineItem(lineItemModel: LineItemModel) {
        withContext(Dispatchers.IO) {
            productRepository.updateLineItemQuantityById(lineItemModel.quantity!!, lineItemModel.id!!)
        }
    }

    private suspend fun removeLineItem(lineItemModel: LineItemModel) {
        withContext(Dispatchers.IO) {
            productRepository.removeLineItemById(lineItemModel.id!!)
        }
    }
}