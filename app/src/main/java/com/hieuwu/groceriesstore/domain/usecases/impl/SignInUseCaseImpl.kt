package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import timber.log.Timber
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    SignInUseCase {
    override suspend fun execute(input: SignInUseCase.Input): SignInUseCase.Output {
        return try {
            val res = userRepository.authenticate(input.email, input.password)
            SignInUseCase.Output(res)
        } catch (e: Exception) {
            Timber.d(e.message)
            SignInUseCase.Output.AccountNotExistedError
        }
    }
}