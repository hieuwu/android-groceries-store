package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserSettingsUseCaseImpl (
    private val userRepository: UserRepository,
    private val ioDispatcher: CoroutineDispatcher
) : UserSettingsUseCase {

    override suspend fun invoke(input: UserSettingsUseCase.Input): UserSettingsUseCase.Output {
        withContext(ioDispatcher) {
            userRepository.updateUserSettings(
                input.id,
                input.isOrderCreatedEnabled,
                input.isDatabaseRefreshedEnabled,
                input.isPromotionEnabled
            )
        }
        return UserSettingsUseCase.Output()
    }
}