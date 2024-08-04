package com.hieuwu.groceriesstore.usecase

interface UserSettingsUseCase : SuspendUseCase<UserSettingsUseCase.Input, UserSettingsUseCase.Output> {
    class Input(
        val id: String,
        val isOrderCreatedEnabled: Boolean,
        val isDatabaseRefreshedEnabled: Boolean,
        val isPromotionEnabled: Boolean
    )

    class Output
}
