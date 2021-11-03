package com.hieuwu.groceriesstore.presentation.updateprofile

import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject

class UpdateProfileViewModel @Inject constructor(private val userRepository: UserRepository) :
    ObservableViewModel() {

}