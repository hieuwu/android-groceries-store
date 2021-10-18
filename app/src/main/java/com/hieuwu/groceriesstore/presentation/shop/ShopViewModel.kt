package com.hieuwu.groceriesstore.presentation.shop

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {
    private var _productList:MutableLiveData<List<ProductModel>> = productRepository.products
            as MutableLiveData<List<ProductModel>>
    val productList: LiveData<List<ProductModel>>
        get() = _productList

    var CurrentCart: MutableLiveData<Order> =
        orderRepository.getCart(OrderStatus.IN_CART).asLiveData() as MutableLiveData<Order>

    init {
        fetchProductsFromServer()
    }


    private fun fetchProductsFromServer() {
        viewModelScope.launch {
            getProductFromServer()
        }
    }

    private suspend fun getProductFromServer() {
        return withContext(Dispatchers.IO) {
            productRepository.getFromServer()
        }
    }

    private val _navigateToSelectedProperty = MutableLiveData<ProductModel?>()
    val navigateToSelectedProperty: LiveData<ProductModel?>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(marsProperty: ProductModel) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun addToCart(product: ProductModel) {
        if (CurrentCart.value != null) {
            //Add to cart
            val cartId = CurrentCart.value!!.id
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.id, cartId, 1, product.price!!
                )
                orderRepository.addLineItem(lineItem)
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value, null)
            viewModelScope.launch {
                orderRepository.insert(newOrder)
                val lineItem = LineItem(
                    product.id, id, 1, product.price!!
                )
                orderRepository.addLineItem(lineItem)
            }
        }
    }

}