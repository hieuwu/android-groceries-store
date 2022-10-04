package com.hieuwu.groceriesstore.presentation.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {
    val productList: StateFlow<List<ProductModel>> =
        getProductListUseCase.getProductList()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _navigateToSelectedProperty = MutableStateFlow<ProductModel?>(null)
    val navigateToSelectedProperty: StateFlow<ProductModel?>
        get() = _navigateToSelectedProperty.asStateFlow()

    var currentCart: StateFlow<OrderModel?> = getProductListUseCase.getCurrentCart()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun displayPropertyDetails(marsProperty: ProductModel) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun addToCart(product: ProductModel) {
        if (currentCart.value != null) {
            // Add to cart
            val cartId = currentCart.value!!.id
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
