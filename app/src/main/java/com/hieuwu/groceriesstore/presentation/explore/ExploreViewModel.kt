package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.ExploreProductUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val exploreProductUseCase: ExploreProductUseCase,
) : ObservableViewModel() {
    private var _currentCart: MutableLiveData<OrderModel> =
        exploreProductUseCase.getCurrentCart() as MutableLiveData<OrderModel>

    private var _categories =
        exploreProductUseCase.getCategoryList() as MutableLiveData<List<CategoryModel>>
    val categories: MutableLiveData<List<CategoryModel>>
        get() = _categories

    private val searchString: MutableLiveData<String> = MutableLiveData<String>("")

    fun searchNameChanged(name: String) {
        searchString.value = name
    }

    val productList: LiveData<List<ProductModel>> =
        Transformations.switchMap(searchString) { string ->
            if (string.isNotEmpty()) exploreProductUseCase.searchProductByName(string)
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

    fun addToCart(product: ProductModel) {
        if (_currentCart.value != null) {
            //Add to cart
            val cartId = _currentCart.value!!.id
            viewModelScope.launch {
                val lineItem = LineItem(
                    product.id, cartId, 1, product.price!!
                )
                exploreProductUseCase.addToCart(lineItem)
            }
        } else {
            val id = UUID.randomUUID().toString()
            val newOrder = Order(id, OrderStatus.IN_CART.value, "")
            viewModelScope.launch {
                exploreProductUseCase.createNewOrder(newOrder)
                val lineItem = LineItem(
                    product.id, id, 1, product.price!!
                )
                exploreProductUseCase.addToCart(lineItem)
            }
        }

    }

}