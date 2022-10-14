package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserSettingsUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UserSettingsUseCase {

    override suspend fun execute(input: UserSettingsUseCase.Input): UserSettingsUseCase.Output {
        withContext(Dispatchers.IO) {
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