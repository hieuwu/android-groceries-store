package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

class CartViewModel @Inject constructor(
    private val updateCartItemUseCase: UpdateCartItemUseCase
) : ObservableViewModel() {

    private var _order: MutableLiveData<OrderModel> =
        updateCartItemUseCase.getCurrentCart() as MutableLiveData<OrderModel>
    val order: LiveData<OrderModel>
        get() = _order

    fun decreaseQty(lineItemModel: LineItemModel) {
        try {
            lineItemModel.decreaseQuantity()
            viewModelScope.launch {
                updateCartItemUseCase.updateLineItem(lineItemModel)
            }
        } catch (ex: Exception) {
            Timber.e(ex.message)
        }
    }

    fun increaseQty(lineItemModel: LineItemModel) {
        try {
            lineItemModel.increaseQuantity()
            viewModelScope.launch {
                updateCartItemUseCase.updateLineItem(lineItemModel)
            }
        } catch (ex: Exception) {
            Timber.e(ex.message)
        }
    }

    fun removeItem(lineItemModel: LineItemModel) {
        try {
            lineItemModel.decreaseQuantity()
            viewModelScope.launch {
                updateCartItemUseCase.removeLineItem(lineItemModel)
            }
        } catch (ex: Exception) {
            Timber.e(ex.message)
        }
    }
}
