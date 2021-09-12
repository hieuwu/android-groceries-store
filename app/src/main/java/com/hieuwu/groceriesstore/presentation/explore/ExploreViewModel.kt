package com.hieuwu.groceriesstore.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject

class ExploreViewModel @Inject constructor() : ObservableViewModel() {
    private var _categories = MutableLiveData<List<Category>>()
    val categories: MutableLiveData<List<Category>>
        get() = _categories

    init {
        val listOfCategories = mutableListOf<Category>()
        listOfCategories.add(Category("1", "Rice", "213"))
        listOfCategories.add(Category("2", "Fish", "213"))
        listOfCategories.add(Category("3", "Oil", "213"))
        listOfCategories.add(Category("4", "Fruit", "213"))
        categories.value = listOfCategories
        _categories.value = listOfCategories

    }
}