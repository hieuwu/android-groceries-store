package com.hieuwu.groceriesstore.presentation.shop

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {
    private var _productList:MutableLiveData<List<ProductModel>> = getProductListUseCase.getProductList()
            as MutableLiveData<List<ProductModel>>
    val productList: LiveData<List<ProductModel>>
        get() = _productList

    private val _navigateToSelectedProperty = MutableLiveData<ProductModel?>()
    val navigateToSelectedProperty: LiveData<ProductModel?>
        get() = _navigateToSelectedProperty

    var currentCart: LiveData<OrderModel>? = getProductListUseCase.getCurrentCart()

    fun displayPropertyDetails(marsProperty: ProductModel) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun addToCart(product: ProductModel) {
        if (currentCart?.value != null) {
            //Add to cart
            val cartId = currentCart?.value!!.id
            viewModelScope.launch {
                getProductListUseCase
                    .addToCart(LineItem(product.id, cartId, 1, product.price!!))
            }
        } else {
            val id = UUID.randomUUID().toString()
            viewModelScope.launch {
                getProductListUseCase.createNewOrder(Order(id, OrderStatus.IN_CART.value, ""))
                getProductListUseCase.addToCart(LineItem(product.id, id, 1, product.price!!))
            }
        }
    }

}