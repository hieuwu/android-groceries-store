package com.hieuwu.groceriesstore.presentation.productdetail

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val orderRepository: OrderRepository
) : ObservableViewModel() {

    private val args = ProductDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    val product = getProductDetailUseCase.getProductDetail(args.id)

    var currentCart: MutableLiveData<OrderModel> =
        orderRepository.getOneOrderByStatus(OrderStatus.IN_CART) as MutableLiveData<OrderModel>

    private var _qty: Int = 1
    var qty: Int
        @Bindable
        get() = _qty
        set(value) {
            _qty = value
            notifyPropertyChanged(BR.qty)
        }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun addToCart() {
        val subtotal = product.value?.price?.times(qty) ?: 0.0
        if (currentCart?.value != null) {
            // Add to cart
            val cartId = currentCart?.value!!.id
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.value!!.id, cartId, _qty, subtotal
                )
                orderRepository.addLineItem(lineItem)
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value, "")
            viewModelScope.launch {
                orderRepository.createOrUpdate(newOrder)
                val lineItem = LineItem(
                    product.value!!.id, id, _qty, subtotal
                )
                orderRepository.addLineItem(lineItem)
            }
        }
        _showSnackbarEvent.value = true
    }

    fun increaseQty() {
        qty++
    }

    fun decreaseQty() {
        if (qty <= 1) return
        qty--
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
}
