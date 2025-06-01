package com.hieuwu.groceriesstore.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.database.entities.LineItem
import com.hieuwu.groceriesstore.data.database.entities.Order
import com.hieuwu.groceriesstore.data.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import java.util.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    getProductDetailUseCase: GetProductDetailUseCase,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val args = ProductDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    val product = getProductDetailUseCase.getProductDetail(args.id).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        null
    )

    var currentCart: StateFlow<OrderModel?> =
        orderRepository.getOneOrderByStatus(OrderStatus.IN_CART)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val _quantity = MutableStateFlow(1)
    val quantity: StateFlow<Int>
        get() = _quantity

    init {
        viewModelScope.launch {
            currentCart.collect {}
        }
    }

    fun addToCart() {
        viewModelScope.launch {
            val subtotal = product.value?.price?.times(_quantity.value) ?: 0.0
            if (currentCart.value != null) {
                // Add to cart
                val cartId = currentCart.value!!.id
                val lineItem = LineItem(
                    productId = product.value!!.id,
                    orderId = cartId,
                    quantity = _quantity.value,
                    subtotal = subtotal
                )
                orderRepository.addLineItem(lineItem)
            } else {
                val id = UUID.randomUUID().toString()
                val newOrder = Order(
                    id = id,
                    status = OrderStatus.IN_CART.value,
                    address = ""
                )
                orderRepository.createOrUpdate(newOrder)
                val lineItem = LineItem(
                    productId = product.value!!.id,
                    orderId = id,
                    quantity = _quantity.value,
                    subtotal = subtotal
                )
                orderRepository.addLineItem(lineItem)
            }
        }
    }

    fun increaseQty() {
        _quantity.value++
    }

    fun decreaseQty() {
        if (_quantity.value <= 1) return
        _quantity.value--
    }

}
