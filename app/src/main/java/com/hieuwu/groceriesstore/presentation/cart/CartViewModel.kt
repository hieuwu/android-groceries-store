package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.data.entities.ProductAndLineItem
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

    private var _order = MutableLiveData<OrderWithLineItems>()
    val order: LiveData<OrderWithLineItems>
        get() = _order

    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice

    init {
        getLineItemFromDatabase()
        sumPrice()
    }

    private fun getLineItemFromDatabase() {
        viewModelScope.launch {
            getLineItemFromLocal()
        }
    }

    private suspend fun getLineItemFromLocal() {
        return withContext(Dispatchers.IO) {
            _order = orderRepository.getCartWithLineItems(OrderStatus.IN_CART)
                .asLiveData() as MutableLiveData<OrderWithLineItems>
        }
    }

    fun sumPrice() {
        var sum = 0.0
        if (_order.value?.lineItemList != null) {
            for (item in _order.value?.lineItemList!!) {
                val sub = item.lineItem?.subtotal ?: 0.0
                sum = sum.plus(sub)
            }
        }
        _totalPrice.value = sum
    }


    fun decreaseQty(lineItemModel: ProductAndLineItem) {
        Timber.d("Minus Clicked")
        if (lineItemModel.lineItem?.quantity == 1) return
        val qty = lineItemModel.lineItem?.quantity?.minus(1)
        if (qty != null) {
            lineItemModel.lineItem.quantity = qty
            lineItemModel.lineItem.subtotal = qty * (lineItemModel.product?.price ?: 1.0)
        }
        viewModelScope.launch {
            updateLineItem(lineItemModel)
        }
    }

    fun increaseQty(lineItemModel: ProductAndLineItem) {
        Timber.d("Plus Clicked")
        val qty = lineItemModel.lineItem?.quantity?.plus(1)
        if (qty != null) {
            lineItemModel.lineItem.quantity = qty
            lineItemModel.lineItem.subtotal = qty * (lineItemModel.product?.price ?: 1.0)
        }
        viewModelScope.launch {
            updateLineItem(lineItemModel)
        }
    }

    fun removeItem(lineItemModel: ProductAndLineItem) {
        viewModelScope.launch {
            removeLineItem(lineItemModel)
        }
    }

    private suspend fun updateLineItem(lineItemModel: ProductAndLineItem) {
        withContext(Dispatchers.IO) {
            productRepository.updateLineItem(lineItemModel.lineItem!!)
        }
    }

    private suspend fun removeLineItem(lineItemModel: ProductAndLineItem) {
        withContext(Dispatchers.IO) {
            productRepository.removeLineItem(lineItemModel.lineItem!!)
        }
    }
}