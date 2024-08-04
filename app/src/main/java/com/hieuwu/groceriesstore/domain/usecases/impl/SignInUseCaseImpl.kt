package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    SignInUseCase {
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