package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.LiveData
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

    private val _productSyncStatus = MutableLiveData<Boolean?>(false)
    val productSyncStatus: LiveData<Boolean?>
        get() = _productSyncStatus

    private val _categorySyncStatus = MutableLiveData<Boolean>(false)
    val categorySyncStatus: MutableLiveData<Boolean>
        get() = _categorySyncStatus
    init {
        viewModelScope.launch {
            getCategories()
            productRepository.getFromServer()
        }
        updateSyncStatus(true)
    }

    fun updateSyncStatus(status: Boolean) {

        _productSyncStatus.value = status
        _categorySyncStatus.value = status
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            categoryRepository.getFromServer()
        }
    }
}