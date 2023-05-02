package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    SignOutUseCase {
    override suspend fun execute(input: SignOutUseCase.Input): SignOutUseCase.Output {
        return withContext(Dispatchers.IO) {
            userRepository.clearUser()
            SignOutUseCase.Output()
        }
    }
}