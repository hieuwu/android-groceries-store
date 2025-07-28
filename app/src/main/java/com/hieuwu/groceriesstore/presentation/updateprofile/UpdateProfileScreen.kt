package com.hieuwu.groceriesstore.presentation.updateprofile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.account.widgets.DemoUser
import com.hieuwu.groceriesstore.presentation.updateprofile.widgets.UpdateProfileAppBar
import com.hieuwu.groceriesstore.presentation.updateprofile.widgets.UpdateProfileScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpdateProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    viewModel: UpdateProfileViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        UpdateProfileScreenView(
            modifier = modifier,
            uiState = uiState,
            onBackClick = onBackClick,
            onSaveClick = { viewModel.updateUserProfile() },
            onNameChanged = viewModel::updateName,
            onPhoneChanged = viewModel::updatePhone,
            onEmailChanged = viewModel::updateEmail,
            onAddressChanged = viewModel::updateAddress,
            onShowMessage = viewModel::onShowMessage,
        )

        if (uiState.isLoading) {
            Dialog(
                onDismissRequest = {},
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun UpdateProfileScreenView(
    modifier: Modifier = Modifier,
    uiState: UpdateProfileUiState,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onNameChanged: (String) -> Unit = {},
    onPhoneChanged: (String) -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    onAddressChanged: (String) -> Unit = {},
    onShowMessage: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    LaunchedEffect(uiState.isUpdateSuccess) {
        if (uiState.isUpdateSuccess != null) {
            if (uiState.isUpdateSuccess == true) {
                snackbarHostState.showSnackbar(context.getString(R.string.update_profile_successfully))
            } else if (uiState.isUpdateSuccess == false) {
                snackbarHostState.showSnackbar(context.getString(R.string.update_profile_failed))
            }
            onShowMessage()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            UpdateProfileAppBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    focusManager.clearFocus()
                    onSaveClick()
                },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 48.dp),
            )
        },
    ) { contentPadding ->
        UpdateProfileScreenContent(
            modifier = Modifier.padding(contentPadding),
            user = uiState.user,
            onNameChanged = onNameChanged,
            onPhoneChanged = onPhoneChanged,
            onEmailChanged = onEmailChanged,
            onAddressChanged = onAddressChanged,
            isInvalidEmail = uiState.isInvalidEmail
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UpdateProfileScreenPreview() {
    UpdateProfileScreenView(
        uiState = UpdateProfileUiState(user = DemoUser),
    )
}