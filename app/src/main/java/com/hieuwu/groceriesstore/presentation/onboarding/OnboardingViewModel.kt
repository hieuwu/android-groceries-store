package com.hieuwu.groceriesstore.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel

class OnboardingViewModel: ObservableViewModel() {
    init {
        //Sync product



        //Sync category
    }

    private val _productSyncStatus = MutableLiveData<Boolean?>()
    val productSyncStatus: LiveData<Boolean?>
        get() = _productSyncStatus

    private val _categorySyncStatus = MutableLiveData<Boolean?>()
    val categorySyncStatus: LiveData<Boolean?>
        get() = _categorySyncStatus

}