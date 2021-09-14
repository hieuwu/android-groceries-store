package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject

class ExploreViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ObservableViewModel() {
    private var _categories: MutableLiveData<List<Category>> =
        categoryRepository.getFromLocal().asLiveData() as MutableLiveData<List<Category>>

    val categories: MutableLiveData<List<Category>>
        get() = _categories

    init {
        categoryRepository.getFromServer()
    }
}