package com.hieuwu.groceriesstore.presentation.viewmodels

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var hasProduct: Boolean = false
    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList


    init {
        fetchProductsFromServer()
        getProductsFromDatabase()
    }

    private fun fetchProductsFromServer() {
        viewModelScope.launch {
            getProductFromServer()
        }
    }

    private fun hasProduct() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                hasProduct = productRepository.hasProduct()
            }
        }
    }

    private fun getProductsFromDatabase() {
        viewModelScope.launch {
            getProductFromLocal()
        }
    }

    private suspend fun getProductFromLocal() {
        return withContext(Dispatchers.IO) {
            _productList =
                productRepository.getAllProducts().asLiveData() as MutableLiveData<List<Product>>
        }
    }

    private suspend fun getProductFromServer() {
        return withContext(Dispatchers.IO) {
            productRepository.getFromServer()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}