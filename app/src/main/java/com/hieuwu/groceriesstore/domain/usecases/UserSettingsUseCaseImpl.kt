package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import javax.inject.Inject

class UserSettingsUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UserSettingsUseCase {

}