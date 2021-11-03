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

    private val _isSyncedSuccessful = MutableLiveData<Boolean?>(false)
    val IsSyncedSuccessful: LiveData<Boolean?>
        get() = _isSyncedSuccessful

    init {
        try {
            viewModelScope.launch {
                getCategories()
                productRepository.getFromServer()
            }
        } catch(e: Exception) {
            updateSyncStatus(true)
        }
    }

    fun updateSyncStatus(status: Boolean) {
        _isSyncedSuccessful.value = status
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            categoryRepository.getFromServer()
        }
    }
}