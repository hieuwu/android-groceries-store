package com.hieuwu.groceriesstore.domain.usecases

interface SuspendUseCase<Input, Output> {
    suspend operator fun invoke(input: Input): Output
}

interface UseCase<Input, Output> {
    operator fun invoke(input: Input): Output
}
