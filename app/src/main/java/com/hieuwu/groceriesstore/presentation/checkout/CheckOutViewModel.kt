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

    init {
        getLineItemFromDatabase()
    }

    private fun getLineItemFromDatabase() {
        viewModelScope.launch {
            getLineItemFromLocal()
        }
    }

    private suspend fun getLineItemFromLocal() {
        return withContext(Dispatchers.IO) {
            _order = orderRepository.getCartWithLineItems(OrderStatus.IN_CART).asLiveData() as MutableLiveData<OrderWithLineItems>
        }
    }

}