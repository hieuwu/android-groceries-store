package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.models.CategoryModel
import com.hieuwu.groceriesstore.models.OrderModel
import com.hieuwu.groceriesstore.models.ProductModel
import com.hieuwu.groceriesstore.models.OrderStatus
import com.hieuwu.groceriesstore.usecase.AddToCartUseCase
import com.hieuwu.groceriesstore.usecase.CreateNewOrderUseCase
import com.hieuwu.groceriesstore.usecase.GetCategoriesListUseCase
import com.hieuwu.groceriesstore.usecase.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.usecase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getCurrentCartUseCase: GetCurrentCartUseCase,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
    private val searchProductUseCase: SearchProductUseCase,
    private val createNewOrderUseCase: CreateNewOrderUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _currentCart: StateFlow<OrderModel?> =
        getCurrentCard()!!
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private var _categories: StateFlow<List<CategoryModel>?> =
        getCategoriesList()!!.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)!!
    val categories: StateFlow<List<CategoryModel>?>
        get() = _categories

    private val _productList = MutableStateFlow<List<ProductModel>?>(null)
    val productList: StateFlow<List<ProductModel>?> = _productList

    private val _searchString = MutableStateFlow("")
    val searchString: StateFlow<String> = _searchString

    fun searchNameChanged(name: String) {
        _searchString.value = name
        searchProduct(name)
    }

    fun clearInput() {
        _searchString.value = ""
        _productList.value = null
    }

    init {
        viewModelScope.launch {
            _currentCart.collect {}
        }
    }

    fun searchProduct(name: String) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                val res =
                    searchProductUseCase(SearchProductUseCase.Input(name = name.trim()))
                res.result.collect {
                    _productList.value = it
                }
            }
        }
    }

    private fun getCurrentCard(): Flow<OrderModel?>? {
        var res: Flow<OrderModel?>? = null
        viewModelScope.launch {
            res = getCurrentCartUseCase(GetCurrentCartUseCase.Input()).result
        }
        return res
    }

    private fun getCategoriesList(): Flow<List<CategoryModel>>? {
        var res: Flow<List<CategoryModel>>? = null
        viewModelScope.launch {
            res = getCategoriesListUseCase(GetCategoriesListUseCase.Input()).result
        }
        return res
    }

    fun addToCart(product: ProductModel) {
        if (_currentCart.value != null) {
            // Add to cart
            val cartId = _currentCart.value!!.id
            viewModelScope.launch {
                addToCartUseCase(
                    AddToCartUseCase.Input(
                        productId = product.id,
                        orderId = cartId,
                        quantity = 1,
                        subtotal = product.price!!
                    )
                )
            }
        } else {
            val id = UUID.randomUUID().toString()
            viewModelScope.launch {
                createNewOrderUseCase(
                    CreateNewOrderUseCase.Input(
                        id = id,
                        status = OrderStatus.IN_CART.value,
                        address = ""
                    )
                )
                addToCartUseCase(
                    AddToCartUseCase.Input(
                        productId = product.id,
                        orderId = id,
                        quantity = 1,
                        subtotal = product.price!!
                    )
                )
            }
        }
    }
}
