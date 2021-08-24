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
import kotlinx.coroutines.launch
import java.util.*

import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    id: String,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : ObservableViewModel() {

    val product = productRepository.getById(id).asLiveData()

    private var _qty: Int = 0
    var qty: Int
        @Bindable
        get() {
            return _qty
        }
        set(value) {
            _qty = value
            notifyPropertyChanged(BR.qty)
        }

    fun addToCart() {
        val subtotal = product.value?.price?.times(qty)
        val hasCart = orderRepository.hasCart().asLiveData()
        if (hasCart.value!!) {
            //Add to cart
            val currentCart = orderRepository.getOrderById()
            var lineItem = LineItem(product.value!!.id, currentCart!!.id, _qty, subtotal!!)
            viewModelScope.launch {
                orderRepository.addLineItem(lineItem)
            }

        } else {
            var id = UUID.randomUUID().toString()
            var newOrder = Order(id, OrderStatus.IN_CART.value)
            viewModelScope.launch {
                orderRepository.insert(newOrder)
            }
            var lineItem = LineItem(product.value!!.id, newOrder.id, _qty, subtotal!!)
            viewModelScope.launch {
                orderRepository.addLineItem(lineItem)
            }
        }
    }

    fun removeFromCart() {


    }

    fun increaseQty() {
        qty++
    }

    fun decreaseQty() {
        if (qty <= 0) return
        qty--
    }

}