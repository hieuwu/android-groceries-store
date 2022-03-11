package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val updateCartItemUseCase: UpdateCartItemUseCase
) : ObservableViewModel() {

    private var _order: MutableLiveData<OrderModel> =
        updateCartItemUseCase.getCurrentCart() as MutableLiveData<OrderModel>
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
            updateCartItemUseCase.updateLineItem(lineItemModel)
        }
    }

    private suspend fun removeLineItem(lineItemModel: LineItemModel) {
        withContext(Dispatchers.IO) {
            updateCartItemUseCase.removeLineItem(lineItemModel)
        }
    }
}