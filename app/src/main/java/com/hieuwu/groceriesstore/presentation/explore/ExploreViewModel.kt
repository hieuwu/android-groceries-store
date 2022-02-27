package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
) : ObservableViewModel() {
    private var _categories =
        categoryRepository.getFromLocal() as MutableLiveData<List<CategoryModel>>
    val categories: MutableLiveData<List<CategoryModel>>
        get() = _categories

    private val searchString: MutableLiveData<String> = MutableLiveData<String>("")

    fun searchNameChanged(name: String) {
        searchString.value = name
    }

    val productList: LiveData<List<ProductModel>> =
        Transformations.switchMap(searchString) { string ->
            if (string.isNotEmpty()) productRepository.searchProductsListByName(string)
            else MutableLiveData()
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

    private var _currentCart: MutableLiveData<OrderModel> =
        orderRepository.getOneOrderByStatus(OrderStatus.IN_CART) as MutableLiveData<OrderModel>

    fun addToCart(product: ProductModel) {
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
            val newOrder = Order(id, OrderStatus.IN_CART.value, "")
            viewModelScope.launch {
                orderRepository.createOrUpdate(newOrder)
                val lineItem = LineItem(
                    product.id, id, 1, product.price!!
                )
                orderRepository.addLineItem(lineItem)
            }
        }

    }

}