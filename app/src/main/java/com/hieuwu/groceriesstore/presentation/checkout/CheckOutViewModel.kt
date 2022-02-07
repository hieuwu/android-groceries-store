package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CheckOutViewModel @Inject constructor(
    private val orderId: String,
    val orderRepository: OrderRepository,
    val userRepository: UserRepository
) :
    ObservableViewModel() {


    private val _user =
        userRepository.getCurrentUser() as MutableLiveData<UserModel>
    val user: LiveData<UserModel?>
        get() = _user

    private var _order = MutableLiveData<OrderModel>()
    val order: LiveData<OrderModel>
        get() = _order

    private var _address = MutableLiveData<Double>()
    val address: LiveData<Double>
        get() = _address

    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice


    private var _isOrderSentSuccessful = MutableLiveData<Boolean>()
    val isOrderSentSuccessful: LiveData<Boolean>
        get() = _isOrderSentSuccessful

    init {
        getLineItemFromDatabase()
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

    private fun getLineItemFromDatabase() {
        viewModelScope.launch {
            getLineItemFromLocal()
        }
    }

    private suspend fun getLineItemFromLocal() {
        return withContext(Dispatchers.IO) {
            _order = orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
             as MutableLiveData<OrderModel>
        }
    }

    private fun setOrderAddress() {
        _order.value?.address = user.value?.address ?: ""
    }

    private suspend fun sendOrderToServer() {
        var res = false
        withContext(Dispatchers.IO) {
            res = orderRepository.sendOrderToServer(order.value!!)
        }
        _isOrderSentSuccessful.value = res
    }

    fun sendOrder() {
        setOrderAddress()
        viewModelScope.launch {
            sendOrderToServer()
        }
    }

}