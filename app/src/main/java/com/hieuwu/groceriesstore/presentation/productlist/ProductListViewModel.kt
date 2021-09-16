package com.hieuwu.groceriesstore.presentation.productlist

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.data.utils.OrderStatus
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    val categoryId: String,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private var viewModelJob = Job()
    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    var CurrentCart: MutableLiveData<Order> =
        orderRepository.getCart(OrderStatus.IN_CART).asLiveData() as MutableLiveData<Order>

    init {
        getProductsFromDatabase()
    }

    private fun getProductsFromDatabase() {
        viewModelScope.launch {

            getProductFromLocal()
        }
    }

    private suspend fun getProductFromLocal() {
        return withContext(Dispatchers.IO) {
            _productList = if (categoryId == null) {
                productRepository.getAllProducts()
                    .asLiveData() as MutableLiveData<List<Product>>
            } else {
                productRepository.getAllProductsByCategory(categoryId)
                    .asLiveData() as MutableLiveData<List<Product>>
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigateToSelectedProperty = MutableLiveData<Product?>()
    val navigateToSelectedProperty: LiveData<Product?>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(marsProperty: Product) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun addToCart(product: Product) {
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