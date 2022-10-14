package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.UserModel

interface GetProfileUseCase:UseCase<GetProfileUseCase.Input, GetProfileUseCase.Output> {
    class Input
    open class Output(val result: LiveData<UserModel?>)
}