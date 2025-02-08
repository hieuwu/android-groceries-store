package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

class SignInUseCaseImpl (
    private val userRepository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : SignInUseCase {
    override suspend fun invoke(input: SignInUseCase.Input): SignInUseCase.Output {
        return withContext(ioDispatcher) {
            try {
                val res = userRepository.authenticate(input.email, input.password)
                SignInUseCase.Output(res)
            } catch (e: Exception) {
                Timber.d(e.message)
                SignInUseCase.Output.AccountNotExistedError
            }
        }
    }
}