package com.hieuwu.groceriesstore.domain.usecases

interface UserSettingsUseCase {
    suspend fun updateUserSettings(
        id: String, isOrderCreatedEnabled: Boolean, isDatabaseRefreshedEnabled: Boolean,
        isPromotionEnabled: Boolean
    )
}