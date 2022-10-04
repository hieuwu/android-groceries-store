package com.hieuwu.groceriesstore.presentation.cart

import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class CartViewModel @Inject constructor(
    private val updateCartItemUseCase: UpdateCartItemUseCase
) : ObservableViewModel() {

    val order: StateFlow<OrderModel?> =
        updateCartItemUseCase.getCurrentCart()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

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
