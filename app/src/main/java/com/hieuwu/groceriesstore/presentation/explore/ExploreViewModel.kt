package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.*
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
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
        get() =  Transformations.switchMap(){ string->
            repository.getCustomerByName(string)


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
        uiScope.launch {
            _productList = getProduct(name) as MutableLiveData
        }
    }

    suspend fun getProduct(name: String): LiveData<List<Product>> {
        return withContext(Dispatchers.IO) {
            productRepository.searchProductsListByName(name).asLiveData()
        }
    }
}