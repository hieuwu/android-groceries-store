package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.entities.LineItem
import com.hieuwu.groceriesstore.domain.entities.Order
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
) : ObservableViewModel() {
    private var _categories: MutableLiveData<List<Category>> =
        categoryRepository.getFromLocal().asLiveData() as MutableLiveData<List<Category>>

    val categories: MutableLiveData<List<Category>>
        get() = _categories

    private val searchString: MutableLiveData<String> = MutableLiveData<String>("")

    fun searchNameChanged(name: String) {
        searchString.value = name
    }

    val productList: LiveData<List<Product>> = Transformations.switchMap(searchString) { string ->
        if (string.isNotEmpty()) productRepository.searchProductsListByName(string).asLiveData()
        else MutableLiveData<List<Product>>()
    }

    private val _navigateToSelectedProperty = MutableLiveData<Product?>()
    val navigateToSelectedProperty: LiveData<Product?>
        get() = _navigateToSelectedProperty


    init {
        viewModelScope.launch {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            categoryRepository.getFromServer()
        }
    }

    fun displayPropertyDetails(marsProperty: Product) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    private var _currentCart: MutableLiveData<Order> =
        orderRepository.getCart(OrderStatus.IN_CART).asLiveData() as MutableLiveData<Order>

    fun addToCart(product: Product) {
        if (_currentCart.value != null) {
            //Add to cart
            val cartId = _currentCart.value!!.id
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