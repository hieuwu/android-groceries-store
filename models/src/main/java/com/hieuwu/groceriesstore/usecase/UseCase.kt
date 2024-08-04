package com.hieuwu.groceriesstore.usecase

interface SuspendUseCase<Input, Output> {
    suspend operator fun invoke(input: Input): Output
}

interface UseCase<Input, Output> {
    operator fun invoke(input: Input): Output
}
