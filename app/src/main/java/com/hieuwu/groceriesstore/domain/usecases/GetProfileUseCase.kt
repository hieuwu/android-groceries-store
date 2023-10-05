package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase:SuspendUseCase<GetProfileUseCase.Input, GetProfileUseCase.Output> {
    class Input
    open class Output(val result: Flow<UserModel?>)
}
