package com.hieuwu.groceriesstore.domain.usecases

interface UseCase<Input, Output> {
    suspend fun execute(input: Input): Output
}