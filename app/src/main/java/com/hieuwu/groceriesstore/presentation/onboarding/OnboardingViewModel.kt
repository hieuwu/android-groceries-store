package com.hieuwu.groceriesstore.presentation.onboarding

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



class OnboardingViewModel constructor(
    private val refreshAppDataUseCase: RefreshAppDataUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _isSyncedSuccessful = MutableStateFlow(false)
    val isSyncedSuccessful: StateFlow<Boolean>
        get() = _isSyncedSuccessful.asStateFlow()

    init {
        val isSyncedSuccessfully = sharedPreferences.getBoolean("PRODUCT_SYNC_SUCCESS", false)
        if (isSyncedSuccessfully) {
            _isSyncedSuccessful.value = true
        } else {
            try {
                viewModelScope.launch {
                    refreshAppDataUseCase(Unit)
                    with(sharedPreferences.edit()) {
                        putBoolean("PRODUCT_SYNC_SUCCESS", true)
                        apply()
                        _isSyncedSuccessful.value = true
                    }
                }
            } catch (e: Exception) {
                _isSyncedSuccessful.value = false
            }
        }
    }
}
