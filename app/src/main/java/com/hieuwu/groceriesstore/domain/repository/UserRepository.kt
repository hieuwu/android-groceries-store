package com.hieuwu.groceriesstore.domain.repository

import com.hieuwu.groceriesstore.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createAccount(email: String, password: String, name: String): Boolean
    suspend fun authenticate(email: String, password: String): Boolean
    fun getCurrentUser(): Flow<UserModel?>
    suspend fun updateUserProfile(userId: String, name: String, email: String, phone: String, address: String)
    suspend fun clearUser()
    suspend fun updateUserSettings(
        id: String, isOrderCreatedEnabled: Boolean, isDatabaseRefreshedEnabled: Boolean,
        isPromotionEnabled: Boolean
    )
}
