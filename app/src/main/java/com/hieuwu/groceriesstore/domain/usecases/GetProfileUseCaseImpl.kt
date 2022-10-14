package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetProfileUseCase {
    override suspend fun execute(input: GetProfileUseCase.Input): GetProfileUseCase.Output {
        val user = userRepository.getCurrentUser()
        return GetProfileUseCase.Output(user)
    }
}