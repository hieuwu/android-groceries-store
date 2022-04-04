package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import javax.inject.Inject

class AuthenticateUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    AuthenticateUserUseCase {
    override suspend fun signIn(email: String, password: String) {
        userRepository.authenticate(email, password)
    }

    override suspend fun signOut() {
        userRepository.clearUser()
    }

    override fun getCurrentUser(): LiveData<UserModel?> {
        return userRepository.getCurrentUser()
    }

    override suspend fun updateUserProfile(
        userId: String,
        name: String,
        email: String,
        phone: String,
        address: String
    ) {
        userRepository.updateUserProfile(
            userId, name, email, phone, address
        )
    }
}
