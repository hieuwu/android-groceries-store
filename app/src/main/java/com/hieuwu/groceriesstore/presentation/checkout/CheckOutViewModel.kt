package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

class CheckOutViewModel @Inject constructor(
    private val orderId: String,
    private val createOrderUseCase: CreateOrderUseCase
) :
    ObservableViewModel() {
    private val _user =
        createOrderUseCase.getCurrentUser() as MutableLiveData<UserModel?>
    val user: LiveData<UserModel?>
        get() = _user

    private var _order: MutableLiveData<OrderModel> =
        createOrderUseCase.getCurrentCart() as MutableLiveData<OrderModel>
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

    private fun setOrderAddress() {
        _order.value?.address = user.value?.address ?: ""
    }

    fun sendOrder() {
        setOrderAddress()
        viewModelScope.launch {
            _isOrderSentSuccessful.value = createOrderUseCase.sendOrderToServer(order.value!!)
        }
    }
}
