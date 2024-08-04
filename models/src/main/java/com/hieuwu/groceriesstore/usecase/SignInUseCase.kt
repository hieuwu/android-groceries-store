package com.hieuwu.groceriesstore.usecase

interface SignInUseCase : SuspendUseCase<SignInUseCase.Input, SignInUseCase.Output> {
    data class Input(val email: String, val password: String)
    open class Output(val result: Boolean) {
        sealed class Error : Output(false)
        object AccountNotExistedError : Output(false)
    }
}
