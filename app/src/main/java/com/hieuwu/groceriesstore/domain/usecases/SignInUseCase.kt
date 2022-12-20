package com.hieuwu.groceriesstore.domain.usecases

interface SignInUseCase : UseCase<SignInUseCase.Input, SignInUseCase.Output> {
    data class Input(val email: String, val password: String)
    open class Output(val result: Boolean) {
        sealed class Error : Output(false)
        object AccountNotExistedError : Output(false)
    }
}