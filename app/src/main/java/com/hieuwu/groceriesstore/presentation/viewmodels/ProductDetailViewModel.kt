package com.hieuwu.groceriesstore.presentation.viewmodels

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.data.utils.OrderStatus
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    id: String,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : ObservableViewModel() {

    val product = productRepository.getById(id).asLiveData()
    val hasCart = orderRepository.hasCart()?.asLiveData()

    private var _currentCart: LiveData<Order> =
        orderRepository.getOrderInCart(OrderStatus.IN_CART).asLiveData()

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

        if (_currentCart.value != null) {
            //Add to cart
            val currentCart = orderRepository.getCurrentCartId(OrderStatus.IN_CART)
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.value!!.id,
                    "da0bce0d-628e-4b77-86db-a70d6ddf7050",
                    _qty,
                    subtotal
                )
                orderRepository.addLineItem(lineItem)
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value)
            viewModelScope.launch {
                orderRepository.insert(newOrder)
                var lineItem = LineItem(
                    product.value!!.id,
                    "da0bce0d-628e-4b77-86db-a70d6ddf7050",
                    _qty,
                    subtotal
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