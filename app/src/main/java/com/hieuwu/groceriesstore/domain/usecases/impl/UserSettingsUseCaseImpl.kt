package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
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