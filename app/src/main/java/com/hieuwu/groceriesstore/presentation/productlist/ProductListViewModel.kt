package com.hieuwu.groceriesstore.presentation.productlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductsByCategoryUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetProductsListUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
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
    private val getProductsListUseCase: GetProductsListUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val createNewOrderUseCase: CreateNewOrderUseCase,
    private val getCurrentCartUseCase: GetCurrentCartUseCase,
) : ViewModel() {

    private val args = ProductListFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val categoryId = args.categoryId

    // TODO: check type for categoryId
    private val _productList: Flow<List<ProductModel>>? = if (categoryId == null) {
        getProductLists()
    } else {
        getProductLists(categoryId)
    }
    val productList: StateFlow<List<ProductModel>> = _productList
        ?.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())!!

    var currentCart: StateFlow<OrderModel?> =
        getCurrentCart()
            ?.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)!!

    private val _navigateToSelectedProperty = MutableStateFlow<ProductModel?>(null)

    val navigateToSelectedProperty: StateFlow<ProductModel?>
        get() = _navigateToSelectedProperty.asStateFlow()

    fun displayProductDetail(product: ProductModel) {
        _navigateToSelectedProperty.value = product
    }

    fun displayProductDetailComplete() {
        _navigateToSelectedProperty.value = null
    }

    private fun getCurrentCart(): Flow<OrderModel?>? {
        var res: Flow<OrderModel?>? = null
        viewModelScope.launch {
            res = getCurrentCartUseCase.execute(GetCurrentCartUseCase.Input()).result
        }
        return res
    }

    private fun getProductLists(): Flow<List<ProductModel>>? {
        var res: Flow<List<ProductModel>>? = null
        viewModelScope.launch {
            res = getProductsListUseCase.execute(GetProductsListUseCase.Input()).result
        }
        return res
    }

    private fun getProductLists(categoryId: String): Flow<List<ProductModel>>? {
        var res: Flow<List<ProductModel>>? = null
        viewModelScope.launch {
            res =
                getProductsByCategoryUseCase.execute(GetProductsByCategoryUseCase.Input(categoryId)).result
        }
        return res
    }

    fun addToCart(product: ProductModel) {
        viewModelScope.launch {
            if (currentCart.value != null) {
                // Add to cart
                addToCartUseCase.execute(
                    AddToCartUseCase.Input(
                        LineItem(
                            productId = product.id,
                            orderId = currentCart.value!!.id,
                            quantity = 1,
                            subtotal = product.price!!
                        )
                    )
                )
            } else {
                // TODO: Move order creation to repository layer, at this point only pass domain object
                val id = UUID.randomUUID().toString()
                val newOrder = Order(id, OrderStatus.IN_CART.value, "")
                createNewOrderUseCase.execute(CreateNewOrderUseCase.Input(newOrder))
                addToCartUseCase.execute(
                    AddToCartUseCase.Input(
                        LineItem(
                            productId = product.id,
                            orderId = id,
                            quantity = 1,
                            subtotal = product.price!!
                        )
                    )
                )
            }
        }
    }
}
