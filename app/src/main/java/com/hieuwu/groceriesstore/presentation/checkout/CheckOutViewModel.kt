package com.hieuwu.groceriesstore.presentation.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import com.hieuwu.groceriesstore.domain.usecases.SubmitOrderUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val getCurrentCartUseCase: GetCurrentCartUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val submitOrderUseCase: SubmitOrderUseCase
) :
    ObservableViewModel() {
    private val _user =
        getCurrentUser()!!.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    val user: StateFlow<UserModel?>
        get() = _user

    val order: StateFlow<OrderModel?> =
        getCurrentCard()!!.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

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

    private fun getCurrentCard(): Flow<OrderModel?>? {
        var res: Flow<OrderModel?>? = null
        viewModelScope.launch {
            res = getCurrentCartUseCase.execute(GetCurrentCartUseCase.Input()).result
        }
        return res
    }

    private fun getCurrentUser(): Flow<UserModel?>? {
        var res: Flow<UserModel?>? = null
        viewModelScope.launch {
            res = getProfileUseCase.execute(GetProfileUseCase.Input()).result
        }
        return res
    }


    fun sendOrder() {
        setOrderAddress()
        viewModelScope.launch {
            _isOrderSentSuccessful.value =
                submitOrderUseCase.execute(SubmitOrderUseCase.Input(order.value!!)).result
        }
    }
}
