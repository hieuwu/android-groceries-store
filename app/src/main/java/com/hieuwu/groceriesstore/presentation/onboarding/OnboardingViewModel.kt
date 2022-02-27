package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.repository.RecipeRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository
) : ObservableViewModel() {

    private val _isSyncedSuccessful = MutableLiveData(false)
    val isSyncedSuccessful: LiveData<Boolean?>
        get() = _isSyncedSuccessful

    init {
        try {
            viewModelScope.launch {
                getCategories()
                productRepository.refreshDatabase()
                recipeRepository.refreshDatabase()
            }
            updateSyncStatus(true)
        } catch(e: Exception) {
            updateSyncStatus(false)
        }
    }

    private fun updateSyncStatus(status: Boolean) {
        _isSyncedSuccessful.value = status
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            categoryRepository.getFromServer()
        }
    }
}