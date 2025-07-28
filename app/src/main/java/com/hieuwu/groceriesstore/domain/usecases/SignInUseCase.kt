package com.hieuwu.groceriesstore.domain.usecases

interface SignInUseCase : SuspendUseCase<SignInUseCase.Input, SignInUseCase.Output> {
    data class Input(val email: String, val password: String)
    open class Output(val result: Boolean) {
        object Success : Output(true)
        object Error : Output(false)
        object AccountNotExistedError : Output(false)
    }
}
