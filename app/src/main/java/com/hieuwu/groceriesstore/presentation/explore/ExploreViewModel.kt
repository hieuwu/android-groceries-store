package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
) :
    ObservableViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _categories: MutableLiveData<List<Category>> =
        categoryRepository.getFromLocal().asLiveData() as MutableLiveData<List<Category>>

    val categories: MutableLiveData<List<Category>>
        get() = _categories

    private var _productList = MutableLiveData<List<Product>>()

    val productList: LiveData<List<Product>>
        get() = _productList


    init {
        uiScope.launch {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            categoryRepository.getFromServer()
        }
    }

    fun searchProductByName(name: String) {

    }

    suspend fun getProduct(name: String): List<Product> {
        return withContext(Dispatchers.IO) {
            productRepository.searchProductsListByName(name)
        }
    }
}