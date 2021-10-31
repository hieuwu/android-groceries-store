package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ObservableViewModel() {

    init {
        viewModelScope.launch {
            getCategories()
            productRepository.getFromServer()
        }
        onSyncDone()
//        _productSyncStatus.value = true
//        categorySyncStatus.value = true

    }

    private fun onSyncDone() {
        _productSyncStatus?.value = true
        _categorySyncStatus?.value = true
    }
    private val _productSyncStatus = MutableLiveData<Boolean?>()
    val productSyncStatus: MutableLiveData<Boolean?>
        get() = _productSyncStatus

    private val _categorySyncStatus = MutableLiveData<Boolean?>()
    val categorySyncStatus: MutableLiveData<Boolean?>
        get() = _categorySyncStatus

    private suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            categoryRepository.getFromServer()
        }
    }
}