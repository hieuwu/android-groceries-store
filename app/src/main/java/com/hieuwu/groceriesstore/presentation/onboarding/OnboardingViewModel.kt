package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val refreshAppDataUseCase: RefreshAppDataUseCase
) : ObservableViewModel() {

    private val _isSyncedSuccessful = MutableStateFlow(false)
    val isSyncedSuccessful: StateFlow<Boolean>
        get() = _isSyncedSuccessful.asStateFlow()

    init {
        try {
            viewModelScope.launch {
                refreshAppDataUseCase.execute(Unit)
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
