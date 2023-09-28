package com.hieuwu.groceriesstore.presentation.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.presentation.authentication.composables.IconTextInput

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    Column {
        Text(text = "Sign In")
        IconTextInput(
            leadingIcon = Icons.Filled.Email,
            trailingIcon = Icons.Filled.Backspace,
            value = email.value,
            placeholder = "Email",
            onValueChange = {
                viewModel.onEmailChange(it)
            },
            onTrailingIconClick = {
                viewModel.onRemoveText()
            },
        )
        Spacer(modifier = modifier.height(12.dp))
        IconTextInput(
            leadingIcon = Icons.Filled.Email,
            trailingIcon = Icons.Filled.Backspace,
            value = password.value,
            placeholder = "Password",
            onValueChange = {
                viewModel.onPasswordChange(it)
            },
            onTrailingIconClick = {},
        )
    }
}
