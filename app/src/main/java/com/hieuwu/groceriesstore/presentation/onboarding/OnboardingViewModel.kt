package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val refreshAppDataUseCase: RefreshAppDataUseCase
) : ObservableViewModel() {

    private val _isSyncedSuccessful = MutableLiveData(false)
    val isSyncedSuccessful: LiveData<Boolean?>
        get() = _isSyncedSuccessful

    init {
        try {
            viewModelScope.launch {
                refreshAppDataUseCase.refreshAppData()
            }
            updateSyncStatus(true)
        } catch (e: Exception) {
            updateSyncStatus(false)
        }
    }

    private fun updateSyncStatus(status: Boolean) {
        _isSyncedSuccessful.value = status
    }
}
