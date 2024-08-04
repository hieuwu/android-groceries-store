package com.hieuwu.groceriesstore.usecase

import com.hieuwu.groceriesstore.models.UserModel
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase:SuspendUseCase<GetProfileUseCase.Input, GetProfileUseCase.Output> {
    class Input
    open class Output(val result: Flow<UserModel?>)
}
