package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    val dinnerMeals: StateFlow<List<Meal>> = _dinnerMeals

    fun onWeekDaySelected(index: Int) {
        for (i in 0 until _days.value.size) {
            _days.value[i].isSelected.value = i == index
        }
    }

    fun onAddMeal(mealType: MealType, name: String, ingredients: List<String>) {
        when (mealType) {
            MealType.BREAKFAST -> onAddBreakfast(name, ingredients)
            MealType.DINNER -> onAddDinner(name, ingredients)
            MealType.LUNCH -> onAddLunch(name, ingredients)
        }
    }

    private fun onAddBreakfast(name: String, ingredients: List<String>) {
        _breakfastMeals.value = addMealToList(
            mealList = _breakfastMeals.value,
            newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients)
        )
    }

    private fun onAddLunch(name: String, ingredients: List<String>) {
        _lunchMeals.value = addMealToList(
            mealList = _lunchMeals.value,
            newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients)
        )
    }

    private fun onAddDinner(name: String, ingredients: List<String>) {
        _dinnerMeals.value = addMealToList(
            mealList = _dinnerMeals.value,
            newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients)
        )
    }

    private fun addMealToList(mealList: List<Meal>, newMeal: Meal): List<Meal> {
        val newList = mutableListOf<Meal>().apply {
            addAll(mealList)
            add(newMeal)
        }
        return newList
    }
}

data class WeekDay(
    val name: String,
    val isSelected: MutableState<Boolean> = mutableStateOf(false),
)

data class Meal(
    val name: String,
    val imageUrl: String,
    val ingredients: List<String> = listOf(),
)

enum class MealType {
    LUNCH, BREAKFAST, DINNER
}