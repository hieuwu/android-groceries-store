package com.hieuwu.groceriesstore.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.repository.OrderRepository
import com.hieuwu.groceriesstore.models.OrderModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductDetailUseCase
import com.hieuwu.groceriesstore.models.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
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
                orderRepository.addLineItem(
                    productId = product.value!!.id,
                    orderId = cartId,
                    quantity = _quantity.value,
                    subtotal = subtotal
                )
            } else {
                val id = UUID.randomUUID().toString()
                orderRepository.createOrUpdate(
                    id = id,
                    status = OrderStatus.IN_CART.value,
                    address = ""
                )
                orderRepository.addLineItem(
                    productId = product.value!!.id,
                    orderId = id,
                    quantity = _quantity.value,
                    subtotal = subtotal
                )
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
