package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.UserModel

interface AuthenticateUserUseCase {
    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
    fun getCurrentUser(): LiveData<UserModel?>
    suspend fun updateUserProfile(userId: String, name: String, email: String, phone: String, address: String)
}
