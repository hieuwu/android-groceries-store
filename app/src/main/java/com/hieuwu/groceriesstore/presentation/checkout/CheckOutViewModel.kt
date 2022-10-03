package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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

    val order: StateFlow<OrderModel?> =
        createOrderUseCase.getCurrentCart()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // TODO: check for type
    private var _address = MutableStateFlow(0.0)
    val address: StateFlow<Double>
        get() = _address.asStateFlow()

    private var _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double>
        get() = _totalPrice.asStateFlow()

    private var _isOrderSentSuccessful = MutableStateFlow<Boolean?>(null)
    val isOrderSentSuccessful: StateFlow<Boolean?>
        get() = _isOrderSentSuccessful.asStateFlow()

    fun sumPrice() {
        var sum = 0.0
        if (order.value?.lineItemList != null) {
            for (item in order.value?.lineItemList!!) {
                val sub = item.subtotal ?: 0.0
                sum = sum.plus(sub)
            }
        }
        _totalPrice.value = sum
    }

    private fun setOrderAddress() {
        order.value?.address = user.value?.address ?: ""
    }

    fun sendOrder() {
        setOrderAddress()
        viewModelScope.launch {
            _isOrderSentSuccessful.value = createOrderUseCase.sendOrderToServer(order.value!!)
        }
    }
}
