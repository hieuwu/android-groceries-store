package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.UserModel

interface UserRepository {
    suspend fun createAccount(email: String, password: String, name: String): Boolean
    suspend fun authenticate(email: String, password: String): Boolean
    fun getCurrentUser(): LiveData<UserModel>
    suspend fun updateUserProfile(userId: String, name: String, email: String, phone: String)

}