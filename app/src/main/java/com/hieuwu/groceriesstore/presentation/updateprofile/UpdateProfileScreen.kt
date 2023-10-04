package com.hieuwu.groceriesstore.presentation.updateprofile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.presentation.account.DemoUser
import com.hieuwu.groceriesstore.presentation.updateprofile.widgets.UpdateProfileAppBar
import com.hieuwu.groceriesstore.presentation.updateprofile.widgets.UpdateProfileScreenContent

@Composable
fun UpdateProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    viewModel: UpdateProfileViewModel = hiltViewModel(),
) {
    val user = viewModel.user.collectAsState()

    UpdateProfileScreenView(
        modifier = modifier,
        user = user.value,
        onBackClick = onBackClick,
        onSaveClick = { viewModel.updateUserProfile() },
        onNameChanged = { viewModel.name = it },
        onPhoneChanged = { viewModel.phoneNumber = it },
        onEmailChanged = { viewModel.email = it },
        onAddressChanged = { viewModel.address = it },
    )
}

@Composable
private fun UpdateProfileScreenView(
    modifier: Modifier = Modifier,
    user: UserModel?,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onNameChanged: (String) -> Unit = {},
    onPhoneChanged: (String) -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onAddressChanged: (String) -> Unit = {},
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            UpdateProfileAppBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
            )
        }
    ) { contentPadding ->
        UpdateProfileScreenContent(
            modifier = Modifier.padding(contentPadding),
            user = user,
            onNameChanged = onNameChanged,
            onPhoneChanged = onPhoneChanged,
            onEmailChanged = onEmailChanged,
            onAddressChanged = onAddressChanged,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UpdateProfileScreenPreview() {
    UpdateProfileScreenView(
        user = DemoUser,
    )
}