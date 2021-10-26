package com.hieuwu.groceriesstore.domain.repository

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.entities.User

interface UserRepository {
    suspend fun createAccount(email: String, password: String, name: String): Boolean
}