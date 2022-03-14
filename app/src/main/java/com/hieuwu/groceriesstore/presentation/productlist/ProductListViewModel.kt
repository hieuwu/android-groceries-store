package com.hieuwu.groceriesstore.presentation.productlist

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.data.entities.LineItem
import com.hieuwu.groceriesstore.data.entities.Order
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.domain.usecases.GetProductListUseCase
import com.hieuwu.groceriesstore.utilities.OrderStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    val categoryId: String,
    val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private var viewModelJob = Job()
    private var _productList = MutableLiveData<List<ProductModel>>()
    val productList: LiveData<List<ProductModel>>
        get() = _productList

    var currentCart: MutableLiveData<OrderModel> =
        getProductListUseCase.getCurrentCart() as MutableLiveData<OrderModel>
    private val _navigateToSelectedProperty = MutableLiveData<ProductModel?>()

    val navigateToSelectedProperty: LiveData<ProductModel?>
        get() = _navigateToSelectedProperty

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
                getProductListUseCase.getProductList()
                        as MutableLiveData<List<ProductModel>>
            } else {
                getProductListUseCase.getAllProductsByCategory(categoryId)
                        as MutableLiveData<List<ProductModel>>
            }
        }
    }

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
            //Add to cart
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