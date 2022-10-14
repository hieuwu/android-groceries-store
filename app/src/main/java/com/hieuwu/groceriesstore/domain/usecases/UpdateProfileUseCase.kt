package com.hieuwu.groceriesstore.domain.usecases

interface UpdateProfileUseCase : UseCase<UpdateProfileUseCase.Input, UpdateProfileUseCase.Output> {
    data class Input(
        val userId: String,
        val name: String,
        val email: String,
        val phone: String,
        val address: String
    )
    class Output
}