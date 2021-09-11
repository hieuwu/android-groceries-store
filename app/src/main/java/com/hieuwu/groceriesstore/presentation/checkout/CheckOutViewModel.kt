package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.utils.OrderStatus
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CheckOutViewModel @Inject constructor(
    private val orderId: String,
    val orderRepository: OrderRepository
) :
    ObservableViewModel() {

    private var _order = MutableLiveData<OrderWithLineItems>()
    val order: LiveData<OrderWithLineItems>
        get() = _order

    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice

    init {
        getLineItemFromDatabase()
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

}