package com.hieuwu.groceriesstore.presentation.productdetail

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    id: String,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : ObservableViewModel() {

    val product = productRepository.getById(id).asLiveData()
    val hasCart = orderRepository.hasCart()?.asLiveData()

    var CurrentCart: MutableLiveData<Order> =
        orderRepository.getCart(OrderStatus.IN_CART).asLiveData() as MutableLiveData<Order>

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
        if (CurrentCart.value != null) {
            //Add to cart
            val cartId = CurrentCart.value!!.id
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.value!!.id, cartId, _qty, subtotal
                )
                orderRepository.addLineItem(lineItem)
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value,null)
            viewModelScope.launch {
                orderRepository.insert(newOrder)
                val lineItem = LineItem(
                    product.value!!.id, id, _qty, subtotal
                )
                orderRepository.addLineItem(lineItem)
            }
        }
        _showSnackbarEvent.value = true
    }

    fun removeFromCart() {


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