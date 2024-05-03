package com.hieuwu.groceriesstore.data.repository.impl

import com.hieuwu.groceriesstore.data.network.dto.Meal
import com.hieuwu.groceriesstore.data.repository.MealPlanRepository
import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.models.MealModel
import com.hieuwu.groceriesstore.utilities.SupabaseHelper
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import java.util.UUID
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class MealPlanRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val userRepository: UserRepository,
    private val storage: Storage
) : MealPlanRepository {
    override suspend fun addMealToPlan(
        weekDay: String,
        name: String,
        ingredients: List<String>,
        mealType: String,
        mealImageUri: ByteArray
    ) {
        val mealId = UUID.randomUUID().toString()
        val userId = userRepository.getCurrentUser().first()?.id ?: ""
        postgrest["meal_plans"].insert<Meal>(
            Meal(
                id = mealId,
                name = name,
                ingredients = ingredients,
                creatorId = userId,
                weekDay = weekDay,
                mealType = mealType,
                image = null,
            )
        )
        val imageKey =
            storage.from("meals").upload(path = "$mealId.png", data = mealImageUri, upsert = true)
        val imageUrl = SupabaseHelper.buildImageUrl(imageKey)
        postgrest["meal_plans"].update({
            set("image", imageUrl)
        }) {
            filter {
                eq("id", mealId)
            }
        }
    }


    override suspend fun retrieveMealByType(type: String, weekDayValue: String): List<MealModel> {
        val userId = userRepository.getCurrentUser().first()?.id ?: ""
        val result = postgrest["meal_plans"].select {
            filter {
                eq("creator", userId)
                eq("week_day", weekDayValue)
                eq("meal_type", type)
            }
        }.decodeList<Meal>().map {
            Timber.d(it.toString())
            MealModel(
                id = it.id,
                name = it.name,
                ingredients = it.ingredients,
                weekDay = it.weekDay,
                creatorId = it.creatorId,
                mealType = it.mealType,
                imageUrl = it.image ?: ""
            )
        }
        return result
    }

    override suspend fun removeMealFromPlan(id: String) {
        postgrest["meal_plans"].delete {
            filter {
                eq("id", id)
            }
        }
    }
}