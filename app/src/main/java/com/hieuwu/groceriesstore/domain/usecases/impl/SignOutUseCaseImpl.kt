package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    SignOutUseCase {
    override suspend fun execute(input: SignOutUseCase.Input): SignOutUseCase.Output {
        userRepository.clearUser()
        return SignOutUseCase.Output()
    }
}