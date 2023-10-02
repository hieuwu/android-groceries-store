package com.hieuwu.groceriesstore.presentation.authentication.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.authentication.composables.IconTextInput

@Composable
fun SignUpScreen(modifier: Modifier = Modifier,
                 viewModel: SignUpViewModel = hiltViewModel()) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.showSignUpSuccessMessage.collect {
            snackbarHostState.showSnackbar("Sign in successfully!")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.showSignUpErrorMessage.collect {
            snackbarHostState.showSnackbar("Sign in failed :( Please try again!")
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = null
            )

            val name = viewModel.name.collectAsState()
            val email = viewModel.email.collectAsState()
            val password = viewModel.password.collectAsState()

            Column(
                modifier = Modifier
                    .padding(top = 320.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                    ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.headlineMedium
                )
                IconTextInput(
                    leadingIcon = Icons.Filled.Person,
                    trailingIcon = Icons.Filled.Backspace,
                    value = name.value,
                    placeholder = "Name",
                    onValueChange = {
                        viewModel.onNameChange(it)
                    },
                    onTrailingIconClick = {
                        viewModel.onNameRemove()
                    },
                )
                IconTextInput(
                    leadingIcon = Icons.Filled.Email,
                    trailingIcon = Icons.Filled.Backspace,
                    value = email.value,
                    placeholder = "Email",
                    onValueChange = {
                        viewModel.onEmailChange(it)
                    },
                    onTrailingIconClick = {
                        viewModel.onEmailRemove()
                    },
                )

                val showPassword = remember { mutableStateOf(false) }
                IconTextInput(
                    leadingIcon = Icons.Filled.Lock,
                    trailingIcon = if (showPassword.value) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    value = password.value,
                    placeholder = "Password",
                    type = KeyboardType.Password,
                    visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    onValueChange = {
                        viewModel.onPasswordChange(it)
                    },
                    onTrailingIconClick = {
                        showPassword.value = !showPassword.value
                    },
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.createAccount() },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimary)),
                ) {
                    Text("Sign up")
                }
            }
        }
    }
}