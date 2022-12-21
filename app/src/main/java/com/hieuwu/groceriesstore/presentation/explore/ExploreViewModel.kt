package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.database.entities.LineItem
import com.hieuwu.groceriesstore.data.database.entities.Order
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.AddToCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.CreateNewOrderUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetCategoriesListUseCase
import com.hieuwu.groceriesstore.domain.usecases.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.domain.usecases.SearchProductUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getCurrentCartUseCase: GetCurrentCartUseCase,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
    private val searchProductUseCase: SearchProductUseCase,
    private val createNewOrderUseCase: CreateNewOrderUseCase,
    private val addToCartUseCase: AddToCartUseCase

) : ObservableViewModel() {
    private val _currentCart: StateFlow<OrderModel?> =
        getCurrentCard()!!
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private var _categories: StateFlow<List<CategoryModel>?> =
        getCategoriesList()!!.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)!!
    val categories: StateFlow<List<CategoryModel>?>
        get() = _categories
    private val searchString: MutableLiveData<String> = MutableLiveData<String>("")

    fun searchNameChanged(name: String) {
        searchString.value = name
    }

    val productList: LiveData<List<ProductModel>> =
        Transformations.switchMap(searchString) { string ->
            if (string.isNotEmpty()) searchProduct(name = string)
            else MutableLiveData()
        }

    private val _navigateToSelectedProperty = MutableLiveData<ProductModel?>()
    val navigateToSelectedProperty: LiveData<ProductModel?>
        get() = _navigateToSelectedProperty

    fun displayProductDetail(product: ProductModel) {
        _navigateToSelectedProperty.value = product
    }

    fun displayProductDetailComplete() {
        _navigateToSelectedProperty.value = null
    }

    init {
        viewModelScope.launch {
            _currentCart.collect{}
        }
    }
    private fun searchProduct(name: String): LiveData<List<ProductModel>> {
        var output: LiveData<List<ProductModel>> = liveData { }
        viewModelScope.launch {
            when (val res = searchProductUseCase.execute(SearchProductUseCase.Input(name = name))) {
                is SearchProductUseCase.Output -> {
                    output = res.result
                }

            }

        }
        return output
    }

    private fun getCurrentCard(): Flow<OrderModel?>? {
        var res: Flow<OrderModel?>? = null
        viewModelScope.launch {
            res = getCurrentCartUseCase.execute(GetCurrentCartUseCase.Input()).result
        }
        return res
    }

    private fun getCategoriesList(): Flow<List<CategoryModel>>? {
        var res: Flow<List<CategoryModel>>? = null
        viewModelScope.launch {
            res = getCategoriesListUseCase.execute(GetCategoriesListUseCase.Input()).result
        }
        return res
    }

    fun addToCart(product: ProductModel) {
        if (_currentCart.value != null) {
            // Add to cart
            val cartId = _currentCart.value!!.id
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.id, cartId, 1, product.price!!
                )
                addToCartUseCase.execute(AddToCartUseCase.Input(lineItem = lineItem))
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value, "")
            viewModelScope.launch {
                createNewOrderUseCase.execute(CreateNewOrderUseCase.Input(order = newOrder))
                val lineItem = LineItem(
                    product.id, id, 1, product.price!!
                )
                addToCartUseCase.execute(AddToCartUseCase.Input(lineItem = lineItem))
            }
        }
    }
}
