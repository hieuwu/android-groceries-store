package com.hieuwu.groceriesstore.presentation.viewmodels

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ObservableViewModel() {
    private var _lineItemList = MutableLiveData<List<ProductAndLineItem>>()
    val lineItemList: LiveData<List<ProductAndLineItem>>
        get() = _lineItemList

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

}