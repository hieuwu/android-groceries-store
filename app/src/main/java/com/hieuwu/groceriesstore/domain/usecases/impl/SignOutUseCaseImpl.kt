package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.SignOutUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignOutUseCaseImpl (
    private val userRepository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher
) : SignOutUseCase {
    override suspend fun invoke(input: SignOutUseCase.Input): SignOutUseCase.Output {
        return withContext(ioDispatcher) {
            userRepository.clearUser()
            SignOutUseCase.Output()
        }
    }
}