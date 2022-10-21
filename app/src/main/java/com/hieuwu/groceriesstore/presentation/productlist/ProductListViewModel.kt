package com.hieuwu.groceriesstore.presentation.productlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ProductListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getProductListUseCase: GetProductListUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    ) : ViewModel() {

    private val args = ProductListFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val categoryId = args.categoryId

    private var viewModelJob = Job()

    // TODO: check type for categoryId
    private val _productList: Flow<List<ProductModel>> = if (categoryId == null) {
        getProductListUseCase.getProductList()
    } else {
        getProductListUseCase.getAllProductsByCategory(categoryId)
    }
    val productList: StateFlow<List<ProductModel>> = _productList
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    var currentCart: StateFlow<OrderModel?> =
        getProductListUseCase.getCurrentCart()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    private val _navigateToSelectedProperty = MutableStateFlow<ProductModel?>(null)

    val navigateToSelectedProperty: StateFlow<ProductModel?>
        get() = _navigateToSelectedProperty.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayProductDetail(product: ProductModel) {
        _navigateToSelectedProperty.value = product
    }

    fun displayProductDetailComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun addToCart(product: ProductModel) {
        if (currentCart.value != null) {
            // Add to cart
            val cartId = currentCart.value!!.id
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.id, cartId, 1, product.price!!
                )
                getProductListUseCase.addToCart(lineItem)
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value, "")
            viewModelScope.launch {
                getProductListUseCase.createNewOrder(newOrder)
                val lineItem = LineItem(
                    product.id, id, 1, product.price!!
                )
                getProductListUseCase.addToCart(lineItem)
            }
        }
    }
}
