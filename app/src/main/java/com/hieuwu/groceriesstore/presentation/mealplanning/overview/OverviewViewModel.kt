package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieuwu.groceriesstore.domain.models.MealModel
import com.hieuwu.groceriesstore.domain.usecases.AddMealToPlanUseCase
import com.hieuwu.groceriesstore.domain.usecases.RemoveMealFromPlanUseCase
import com.hieuwu.groceriesstore.domain.usecases.RetrieveMealByTypeUseCase
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.Meal
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.MealType
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.WeekDay
import com.hieuwu.groceriesstore.presentation.mealplanning.overview.state.WeekDayValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val addMealToPlanUseCase: AddMealToPlanUseCase,
    private val retrieveMealByTypeUseCase: RetrieveMealByTypeUseCase,
    private val removeMealFromPlanUseCase: RemoveMealFromPlanUseCase
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
        retrieveMeal(WeekDayValue.valueOf(_days.value[selectedDayIndex].name))
        _days.value[initialSelectedDay].isSelected.value = true
    }

    private fun retrieveMeal(currentDayValue: WeekDayValue) {
        viewModelScope.launch {
            launch {
                onRetrieveMealByType(
                    weekDayValue = currentDayValue,
                    mealType = MealType.BREAKFAST,
                )
            }

            launch {
                onRetrieveMealByType(
                    weekDayValue = currentDayValue,
                    mealType = MealType.LUNCH,
                )
            }

            launch {
                onRetrieveMealByType(
                    weekDayValue = currentDayValue,
                    mealType = MealType.DINNER,
                )
            }
        }
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
                mapMealByType(mealType = mealType, meals = result.data)
            }

            is RetrieveMealByTypeUseCase.Output.Failure -> {
                _breakfastMeals.value = listOf()
            }
        }
    }

    private fun MealModel.asDomain(): Meal = Meal(
        id = id,
        name = name,
        imageUrl = imageUrl,
        ingredients = ingredients.toList()
    )

    private fun mapMealByType(mealType: MealType, meals: List<MealModel>) {
        when (mealType) {
            MealType.BREAKFAST -> {
                _breakfastMeals.value = meals.map { meal -> meal.asDomain() }
            }

            MealType.LUNCH -> {
                _lunchMeals.value = meals.map { meal -> meal.asDomain() }
            }

            MealType.DINNER -> {
                _dinnerMeals.value = meals.map { meal -> meal.asDomain() }
            }
        }
    }

    fun onWeekDaySelected(index: Int) {
        selectedDayIndex = index
        for (i in 0 until _days.value.size) {
            _days.value[i].isSelected.value = i == index
        }
        retrieveMeal(WeekDayValue.valueOf(_days.value[selectedDayIndex].name))
    }

    fun onRemoveMeal(id: String) {
        viewModelScope.launch {
            removeMealFromPlanUseCase.execute(
                RemoveMealFromPlanUseCase.Input(
                    id = id
                )
            )
        }
    }

    fun onAddMeal(
        mealType: MealType,
        name: String,
        ingredients: List<String>,
        mealImageUri: ByteArray
    ) {
        when (mealType) {
            MealType.BREAKFAST -> onAddBreakfast(name, ingredients, mealImageUri)
            MealType.DINNER -> onAddDinner(name, ingredients, mealImageUri)
            MealType.LUNCH -> onAddLunch(name, ingredients, mealImageUri)
        }
    }

    private fun onAddBreakfast(name: String, ingredients: List<String>, mealImageUri: ByteArray) {
        viewModelScope.launch {
            addMealToPlanUseCase.execute(
                AddMealToPlanUseCase.Input(
                    name = name,
                    weekDay = _days.value[selectedDayIndex].name,
                    ingredients = ingredients,
                    mealType = MealType.BREAKFAST,
                    mealImageUri = mealImageUri
                )
            )
            _breakfastMeals.value = addMealToList(
                mealList = _breakfastMeals.value,
                newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients, id = "")
            )
        }
    }

    private fun onAddLunch(name: String, ingredients: List<String>, mealImageUri: ByteArray) {
        viewModelScope.launch {
            addMealToPlanUseCase.execute(
                AddMealToPlanUseCase.Input(
                    name = name,
                    weekDay = _days.value[selectedDayIndex].name,
                    ingredients = ingredients,
                    mealType = MealType.LUNCH,
                    mealImageUri = mealImageUri,
                )
            )
            _lunchMeals.value = addMealToList(
                mealList = _lunchMeals.value,
                newMeal = Meal(name = name, imageUrl = "", ingredients = ingredients, id = "")
            )
        }
    }

    private fun onAddDinner(name: String, ingredients: List<String>, mealImageUri: ByteArray) {
        viewModelScope.launch {
            addMealToPlanUseCase.execute(
                AddMealToPlanUseCase.Input(
                    name = name,
                    weekDay = _days.value[selectedDayIndex].name,
                    ingredients = ingredients,
                    mealType = MealType.DINNER,
                    mealImageUri = mealImageUri,
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

