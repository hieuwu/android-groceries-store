package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    SignOutUseCase {
    override suspend fun execute(input: SignOutUseCase.Input): SignOutUseCase.Output {
        userRepository.clearUser()
        return SignOutUseCase.Output()
    }
}