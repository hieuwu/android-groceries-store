package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor() : ViewModel() {

    private val _days = MutableStateFlow(
        mutableListOf(
            WeekDay("Mon"), WeekDay("Tue"), WeekDay("Wed"),
            WeekDay("Thu"), WeekDay("Fri"), WeekDay("Sat"), WeekDay("Sun")
        )
    )
    val day: StateFlow<List<WeekDay>> = _days

    private val _breakfastMeals = MutableStateFlow<List<Meal>>(value = emptyList())
    val breakfastMeals: StateFlow<List<Meal>> = _breakfastMeals


    private val _lunchMeals = MutableStateFlow<List<Meal>>(value = emptyList())
    val lunchMeals: StateFlow<List<Meal>> = _lunchMeals

    private val _dinnerMeals = MutableStateFlow<List<Meal>>(value = emptyList())

    init {
        viewModelScope.launch {
            val meals = listOf(
                Meal(
                    name = "Chicken Fried Rice",
                    imageUrl = ""
                ),
                Meal(
                    name = "Chicken Fried Rice",
                    imageUrl = ""
                )
            )
            _breakfastMeals.emit(meals)
            _lunchMeals.emit(meals)
            _dinnerMeals.emit(meals)
        }
    }

    val dinnerMeals: StateFlow<List<Meal>> = _dinnerMeals


    fun onWeekDaySelected(index: Int) {
        viewModelScope.launch {
            for (i in 0 until _days.value.size) {
                _days.value[i].isSelected.value = i == index
            }
        }
    }

    fun onAddBreakfast() {

    }

    fun onAddLunch() {

    }

    fun onAddDinner() {

    }
}

data class WeekDay(
    val name: String,
    val isSelected: MutableState<Boolean> = mutableStateOf(false),
)

data class Meal(
    val name: String,
    val imageUrl: String,
)