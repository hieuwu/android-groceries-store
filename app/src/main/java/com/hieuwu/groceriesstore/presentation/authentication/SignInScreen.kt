package com.hieuwu.groceriesstore.presentation.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .weight(1.0f),
            painter = painterResource(id = R.drawable.login_background), contentDescription = null
        )

        val email = viewModel.email.collectAsState()
        val password = viewModel.password.collectAsState()

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1.0f)
                .background(color = Color.White, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
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
            IconTextInput(
                leadingIcon = Icons.Filled.Lock,
                trailingIcon = Icons.Filled.RemoveRedEye,
                value = password.value,
                placeholder = "Password",
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                onTrailingIconClick = {},
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
