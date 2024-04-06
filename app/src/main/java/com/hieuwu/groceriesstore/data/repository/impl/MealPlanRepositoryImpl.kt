package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.network.dto.Meal
import com.hieuwu.groceriesstore.data.repository.MealPlanRepository
import com.hieuwu.groceriesstore.data.repository.UserRepository
import io.github.jan.supabase.postgrest.Postgrest
import java.util.UUID
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class MealPlanRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val userRepository: UserRepository
) : MealPlanRepository {
    override suspend fun addMealToPlan(name: String, ingredients: List<String>) {
        userRepository.getCurrentUser().collect {
            postgrest["meal_plans"].insert<Meal>(
                Meal(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    ingredients = ingredients,
                    creatorId = it?.id ?: "",
                    weekDay = "mon"
                )
            )
        }
    }
}