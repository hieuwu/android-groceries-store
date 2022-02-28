package com.hieuwu.groceriesstore.presentation.productdetail

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    id: String,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val orderRepository: OrderRepository
) : ObservableViewModel() {

    val product = getProductDetailUseCase.getProductDetail(id)

    var currentCart: MutableLiveData<OrderModel> =
        orderRepository.getOneOrderByStatus(OrderStatus.IN_CART) as MutableLiveData<OrderModel>

    private var _qty: Int = 1
    var qty: Int
        @Bindable
        get() {
            return _qty
        }
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
            //Add to cart
            val cartId = currentCart?.value!!.id
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.value!!.id, cartId, _qty, subtotal
                )
                orderRepository.addLineItem(lineItem)
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value,"")
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