package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExploreViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ObservableViewModel() {
    private var _categories: MutableLiveData<List<Category>> =
        categoryRepository.getFromLocal().asLiveData() as MutableLiveData<List<Category>>

    val categories: MutableLiveData<List<Category>>
        get() = _categories

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