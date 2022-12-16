package com.hieuwu.groceriesstore.domain.usecases

interface UserSettingsUseCase : UseCase<UserSettingsUseCase.Input, UserSettingsUseCase.Output> {
    class Input(
        val id: String,
        val isOrderCreatedEnabled: Boolean,
        val isDatabaseRefreshedEnabled: Boolean,
        val isPromotionEnabled: Boolean
    )

    class Output
}