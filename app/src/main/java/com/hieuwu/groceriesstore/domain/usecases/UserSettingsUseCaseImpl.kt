package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserSettingsUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UserSettingsUseCase {

    override suspend fun updateUserSettings(
        id: String,
        isOrderCreatedEnabled: Boolean,
        isDatabaseRefreshedEnabled: Boolean,
        isPromotionEnabled: Boolean
    ) {
        withContext(Dispatchers.IO) {
            userRepository.updateUserSettings(
                id,
                isOrderCreatedEnabled,
                isDatabaseRefreshedEnabled,
                isPromotionEnabled
            )
        }
    }

}