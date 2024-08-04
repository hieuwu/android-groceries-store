package com.hieuwu.groceriesstore.presentation.productlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.models.OrderModel
import com.hieuwu.groceriesstore.models.ProductModel
import com.hieuwu.groceriesstore.models.OrderStatus
import com.hieuwu.groceriesstore.usecase.AddToCartUseCase
import com.hieuwu.groceriesstore.usecase.CreateNewOrderUseCase
import com.hieuwu.groceriesstore.usecase.GetCurrentCartUseCase
import com.hieuwu.groceriesstore.usecase.GetProductsByCategoryUseCase
import com.hieuwu.groceriesstore.usecase.GetProductsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
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
    private val categoryName = args.categoryName

    // TODO: check type for categoryId
    private val _productList: Flow<List<ProductModel>> = if (categoryId == null) {
        getProductLists()
    } else {
        getProductLists(categoryId)
    }

    val state = _productList.map {
        ProductListViewState(
            categoryName,
            it.toImmutableList()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ProductListViewState.Empty
    )

    var currentCart: StateFlow<OrderModel?> =
        getCurrentCart()
            ?.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)!!

    private fun getCurrentCart(): Flow<OrderModel?>? {
        var res: Flow<OrderModel?>? = null
        viewModelScope.launch {
            res = getCurrentCartUseCase(GetCurrentCartUseCase.Input()).result
        }
        return res
    }

    private fun getProductLists(): Flow<List<ProductModel>> {
        return getProductsListUseCase(GetProductsListUseCase.Input()).result
    }

    private fun getProductLists(categoryId: String): Flow<List<ProductModel>> {
        return getProductsByCategoryUseCase(GetProductsByCategoryUseCase.Input(categoryId)).result
    }

    fun addToCart(product: ProductModel) {
        viewModelScope.launch {
            if (currentCart.value != null) {
                // Add to cart
                addToCartUseCase(
                    AddToCartUseCase.Input(
                        productId = product.id,
                        orderId = currentCart.value!!.id,
                        quantity = 1,
                        subtotal = product.price!!
                    )
                )
            } else {
                // TODO: Move order creation to repository layer, at this point only pass domain object
                val id = UUID.randomUUID().toString()
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
