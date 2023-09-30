package com.hieuwu.groceriesstore.presentation.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HideSource
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.authentication.composables.IconTextInput

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.login_background), contentDescription = null
        )

        val email = viewModel.email.collectAsState()
        val password = viewModel.password.collectAsState()

        Column(
            modifier = Modifier
                .padding(top = 320.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                )
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
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
            Spacer(modifier = Modifier.height(12.dp))
            val showPassword = remember { mutableStateOf(false) }
            IconTextInput(
                leadingIcon = Icons.Filled.Lock,
                trailingIcon = if (showPassword.value) Icons.Filled.RemoveRedEye else Icons.Filled.HideSource,
                value = password.value,
                placeholder = "Password",
                type =KeyboardType.Password,
                shouldShowTrailingIcon = showPassword.value,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                onTrailingIconClick = {
                    showPassword.value = !showPassword.value
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimary)),
                border = BorderStroke(width = 3.dp, color = Color.Transparent)
            ) {
                Text("Sign in")
            }
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}
