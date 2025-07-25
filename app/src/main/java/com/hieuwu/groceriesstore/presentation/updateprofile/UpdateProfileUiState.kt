package com.hieuwu.groceriesstore.presentation.updateprofile

import com.hieuwu.groceriesstore.domain.models.UserModel

data class UpdateProfileUiState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val isInvalidEmail: Boolean = false,
    val isUpdateSuccess: Boolean? = null,
)
