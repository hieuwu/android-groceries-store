package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.MealModel
import com.hieuwu.groceriesstore.domain.usecases.AddMealToPlanUseCase
import com.hieuwu.groceriesstore.domain.usecases.RetrieveMealByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val addMealToPlanUseCase: AddMealToPlanUseCase,
    private val retrieveMealByTypeUseCase: RetrieveMealByTypeUseCase,
) : ViewModel() {

    private val _days = MutableStateFlow(
        WeekDayValue.entries.map {
            WeekDay(it.dayValue)
        }.toMutableList()
    )
    val day: StateFlow<List<WeekDay>> = _days

    private val _breakfastMeals = MutableStateFlow<List<Meal>>(value = emptyList())
    val breakfastMeals: StateFlow<List<Meal>> = _breakfastMeals

    private val _lunchMeals = MutableStateFlow<List<Meal>>(value = emptyList())
    val lunchMeals: StateFlow<List<Meal>> = _lunchMeals

    private val _dinnerMeals = MutableStateFlow<List<Meal>>(value = emptyList())
    val dinnerMeals: StateFlow<List<Meal>> = _dinnerMeals

    private val initialSelectedDay: Int = 0
    private var selectedDayIndex = initialSelectedDay

    init {
        viewModelScope.launch {
            launch {
                onRetrieveMealByType(
                    weekDayValue = WeekDayValue.Mon,
                    mealType = MealType.BREAKFAST,
                )
            }

//            launch {
//                onRetrieveMealByType(
//                    weekDayValue = WeekDayValue.Mon,
//                    mealType = MealType.LUNCH,
//                )
//            }
//
//            launch {
//                onRetrieveMealByType(
//                    weekDayValue = WeekDayValue.Mon,
//                    mealType = MealType.DINNER,
//                )
//            }

        }
        _days.value[initialSelectedDay].isSelected.value = true
    }

    private suspend fun onRetrieveMealByType(
        weekDayValue: WeekDayValue,
        mealType: MealType,
    ) {
        val result = retrieveMealByTypeUseCase.execute(
            RetrieveMealByTypeUseCase.Input(
                dayValue = weekDayValue,
                mealType = mealType
            )
        )
        when (result) {
            is RetrieveMealByTypeUseCase.Output.Success -> {
                result.data.collect {
                    when (mealType) {
                        MealType.BREAKFAST -> {
                            _breakfastMeals.emit(it.map { it.asDomain() })
                        }

                        MealType.LUNCH -> {
                            _lunchMeals.emit(it.map { it.asDomain() })
                        }

                        MealType.DINNER -> {
                            _dinnerMeals.emit(it.map { it.asDomain() })
                        }
                    }
                }
            }

            is RetrieveMealByTypeUseCase.Output.Failure -> {
                _breakfastMeals.value = listOf()
            }
        }
    }

    private fun MealModel.asDomain(): Meal = Meal(
        id = id,
        name = name,
        imageUrl = "",
        ingredients = ingredients.toList()
    )

    fun onWeekDaySelected(index: Int) {
        selectedDayIndex = index
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
        viewModelScope.launch {
            addMealToPlanUseCase.execute(
                AddMealToPlanUseCase.Input(
                    name = name,
                    weekDay = _days.value[selectedDayIndex].name,
                    ingredients = ingredients,
                    mealType = MealType.BREAKFAST
                )
            )
            _breakfastMeals.value = addMealToList(
                mealList = _breakfastMeals.value,
                newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients, id = "")
            )
        }
    }

    private fun onAddLunch(name: String, ingredients: List<String>) {
        viewModelScope.launch {
            addMealToPlanUseCase.execute(
                AddMealToPlanUseCase.Input(
                    name = name,
                    weekDay = _days.value[selectedDayIndex].name,
                    ingredients = ingredients,
                    mealType = MealType.LUNCH
                )
            )
            _lunchMeals.value = addMealToList(
                mealList = _lunchMeals.value,
                newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients, id = "")
            )
        }
    }

    private fun onAddDinner(name: String, ingredients: List<String>) {
        viewModelScope.launch {
            addMealToPlanUseCase.execute(
                AddMealToPlanUseCase.Input(
                    name = name,
                    weekDay = _days.value[selectedDayIndex].name,
                    ingredients = ingredients,
                    mealType = MealType.DINNER
                )
            )
            _dinnerMeals.value = addMealToList(
                mealList = _dinnerMeals.value,
                newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients, id = "")
            )
        }
    }

    private fun addMealToList(mealList: List<Meal>, newMeal: Meal): List<Meal> {
        val newList = mutableListOf<Meal>().apply {
            addAll(mealList)
            add(newMeal)
        }
        return newList
    }
}

