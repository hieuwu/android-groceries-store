package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.GetProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetProfileUseCase {
    override suspend fun execute(input: GetProfileUseCase.Input): GetProfileUseCase.Output {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getCurrentUser()
            GetProfileUseCase.Output(user)
        }
    }
}