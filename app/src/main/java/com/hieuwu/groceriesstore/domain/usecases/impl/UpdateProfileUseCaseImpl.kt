package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.di.IoDispatcher
import com.hieuwu.groceriesstore.domain.usecases.UpdateProfileUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateProfileUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) :
    UpdateProfileUseCase {
    override suspend fun execute(input: UpdateProfileUseCase.Input): UpdateProfileUseCase.Output {
        userRepository.updateUserProfile(
            input.userId, input.name, input.email, input.phone, input.address
        )
        return UpdateProfileUseCase.Output()
    }
}