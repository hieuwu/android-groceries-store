package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.*
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
    private var _categories: MutableLiveData<List<Category>> =
        categoryRepository.getFromLocal().asLiveData() as MutableLiveData<List<Category>>

    val categories: MutableLiveData<List<Category>>
        get() = _categories

    private val searchString: MutableLiveData<String> = MutableLiveData<String>("Wagu Beef")

    fun searchNameChanged(name: String) {
        searchString.value = name
    }

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = Transformations.switchMap(searchString) { string ->
            productRepository.searchProductsListByName(string).asLiveData()
        }

    init {
        viewModelScope.launch {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            categoryRepository.getFromServer()
        }
    }

}