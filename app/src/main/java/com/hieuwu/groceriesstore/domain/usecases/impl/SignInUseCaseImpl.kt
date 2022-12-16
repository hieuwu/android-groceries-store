package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    SignInUseCase {
    override suspend fun execute(input: SignInUseCase.Input): SignInUseCase.Output {
        val res = userRepository.authenticate(input.email, input.password)
        return SignInUseCase.Output(res)
    }
}