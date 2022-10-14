package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import javax.inject.Inject

class UpdateProfileUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UpdateProfileUseCase {
    override suspend fun execute(input: UpdateProfileUseCase.Input): UpdateProfileUseCase.Output {
        userRepository.updateUserProfile(
            input.userId, input.name, input.email, input.phone, input.address
        )
        return UpdateProfileUseCase.Output()
    }
}