package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val updateCartItemUseCase: UpdateCartItemUseCase
) : ObservableViewModel() {

    private var _order: MutableLiveData<OrderModel> =
        updateCartItemUseCase.getCurrentCart() as MutableLiveData<OrderModel>
    val order: LiveData<OrderModel>
        get() = _order

    private var _isCartEmpty: Boolean = getOrderStatus()
    val isCartEmpty: Boolean
        get() = _isCartEmpty

    private fun getOrderStatus(): Boolean {
        if (_order == null) return false
        return try {
            _order?.value?.lineItemList?.size!! > 0
        } catch (e: Exception) {
            false
        }
    }

    fun decreaseQty(lineItemModel: LineItemModel) {
        Timber.d("Minus Clicked")
        lineItemModel.decreaseQuantity()
        viewModelScope.launch {
            updateCartItemUseCase.updateLineItem(lineItemModel)
        }
    }

    fun increaseQty(lineItemModel: LineItemModel) {
        Timber.d("Plus Clicked")
        lineItemModel.increaseQuantity()
        viewModelScope.launch {
            updateCartItemUseCase.updateLineItem(lineItemModel)
        }
    }

    fun removeItem(lineItemModel: LineItemModel) {
        lineItemModel.decreaseQuantity()
        viewModelScope.launch {
            updateCartItemUseCase.removeLineItem(lineItemModel)
        }
    }
}