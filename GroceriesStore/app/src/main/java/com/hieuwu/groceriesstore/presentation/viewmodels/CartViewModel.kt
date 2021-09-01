package com.hieuwu.groceriesstore.presentation.viewmodels

import androidx.databinding.Bindable
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.fragments.ShopFragmentDirections
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ObservableViewModel() {
    private var _lineItemList = MutableLiveData<List<ProductAndLineItem>>()
    val lineItemList: LiveData<List<ProductAndLineItem>>
        get() = _lineItemList

//    private var _totalPrice = MutableLiveData<Double>()
//    val totalPrice: LiveData<Double>
//        @Bindable
//        get() = _totalPrice

    private var _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice

    init {
        getLineItemFromDatabase()
    }

    private fun getLineItemFromDatabase() {
        viewModelScope.launch {
            getLineItemFromLocal()
        }
    }

    private suspend fun getLineItemFromLocal() {
        return withContext(Dispatchers.IO) {
            _lineItemList =
                productRepository.getProductAndLineItem()
                    .asLiveData() as MutableLiveData<List<ProductAndLineItem>>
        }
    }

    fun displayPropertyDetails(it: ProductAndLineItem) {

    }

    fun sumPrice(): Double {
        var sum = 0.0
        if (_lineItemList.value != null) {
            for (item in _lineItemList.value!!) {
                val subtotal = item.lineItem?.quantity?.times(item?.product.price!!) ?: 0.0
                sum = sum.plus(subtotal)
            }
        }
        _totalPrice.value = sum
        return 5.0
    }

}