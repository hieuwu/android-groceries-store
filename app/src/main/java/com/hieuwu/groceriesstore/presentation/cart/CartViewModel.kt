package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.data.entities.ProductAndLineItem
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

    private var _order = MutableLiveData<OrderModel>()
    val order: LiveData<OrderModel>
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
            _order =
                orderRepository.getOneOrderByStatus(OrderStatus.IN_CART) as MutableLiveData<OrderModel>
        }
    }

    fun sumPrice() {
        var sum = 0.0
        if (_order.value?.lineItemList != null) {
            for (item in _order.value?.lineItemList!!) {
                val sub = item?.subtotal ?: 0.0
                sum = sum.plus(sub)
            }
        }
        _totalPrice.value = sum
    }


    fun decreaseQty(lineItemModel: LineItemModel) {
        Timber.d("Minus Clicked")
        if (lineItemModel?.quantity == 1) return
        val qty = lineItemModel?.quantity?.minus(1)
        if (qty != null) {
            lineItemModel.quantity = qty
            lineItemModel.subtotal = qty * (lineItemModel?.price ?: 1.0)
        }
        viewModelScope.launch {
            updateLineItem(lineItemModel)
        }
    }

    fun increaseQty(lineItemModel: LineItemModel) {
        Timber.d("Plus Clicked")
        val qty = lineItemModel?.quantity?.plus(1)
        if (qty != null) {
            lineItemModel.quantity = qty
            lineItemModel.subtotal = qty * (lineItemModel?.price ?: 1.0)
        }
        viewModelScope.launch {
            updateLineItem(lineItemModel)
        }
    }

    fun removeItem(lineItemModel: LineItemModel) {
        viewModelScope.launch {
//            removeLineItem(lineItemModel)
        }
    }

    private suspend fun updateLineItem(lineItemModel: LineItemModel) {
        withContext(Dispatchers.IO) {
            productRepository.updateLineItemQuantityById(lineItemModel.quantity!!, lineItemModel.id!!)
        }
    }

    private suspend fun removeLineItem(lineItemModel: ProductAndLineItem) {
        withContext(Dispatchers.IO) {
            productRepository.removeLineItem(lineItemModel.lineItem!!)
        }
    }
}