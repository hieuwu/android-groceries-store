package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.repository.UserRepository
import com.hieuwu.groceriesstore.usecase.GetProfileUseCase
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) :
    GetProfileUseCase {
    override suspend fun invoke(input: GetProfileUseCase.Input): GetProfileUseCase.Output {
        val user = userRepository.getCurrentUser()
        return GetProfileUseCase.Output(user)
    }
}