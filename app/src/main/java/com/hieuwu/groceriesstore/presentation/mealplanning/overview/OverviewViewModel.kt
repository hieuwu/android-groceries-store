package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor() : ViewModel() {

    private val _days = MutableStateFlow(listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"))
    val day: StateFlow<List<String>> = _days


}