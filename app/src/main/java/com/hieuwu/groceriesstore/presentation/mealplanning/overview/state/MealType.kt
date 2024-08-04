package com.hieuwu.groceriesstore.presentation.mealplanning.overview.state

import com.hieuwu.groceriesstore.models.MealType as MealTypeDomain

enum class MealType(val value: String) {
    LUNCH("lunch"), BREAKFAST("breakfast"), DINNER("dinner")
}

 fun MealType.fromStateToDomain(): MealTypeDomain {
    return when (this) {
        MealType.LUNCH -> MealTypeDomain.LUNCH
        MealType.BREAKFAST -> MealTypeDomain.BREAKFAST
        MealType.DINNER -> MealTypeDomain.DINNER
    }
}
