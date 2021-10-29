package com.hieuwu.groceriesstore.domain.repository

interface UserRepository {
    suspend fun createAccount(email: String, password: String, name: String): Boolean
    suspend fun authenticate(email: String, password: String): Boolean

}