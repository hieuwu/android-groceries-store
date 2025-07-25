package com.hieuwu.groceriesstore.presentation.updateprofile.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.presentation.account.widgets.DemoUser

@Composable
fun UpdateProfileScreenContent(
    modifier: Modifier = Modifier,
    user: UserModel?,
    onNameChanged: (String) -> Unit = {},
    onPhoneChanged: (String) -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
    isInvalidEmail: Boolean = false,
    onAddressChanged: (String) -> Unit = {},
) {
    Column(
        modifier = modifier.padding(
            horizontal = dimensionResource(id = R.dimen.margin_medium),
            vertical = dimensionResource(id = R.dimen.margin_small),
        ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.margin_small)
        )
    ) {
        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = user?.name.orEmpty(),
            placeholder = stringResource(id = R.string.name),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            onValueChange = {
                onNameChanged(it)
            }
        )
        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = user?.phone.orEmpty(),
            placeholder = stringResource(id = R.string.phone_number),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            onValueChange = {
                onPhoneChanged(it)
            }
        )
        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = user?.email.orEmpty(),
            placeholder = stringResource(id = R.string.email),
            singleLine = true,
            onValueChange = {
                onEmailChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = isInvalidEmail,
            supportingText = {
                if (isInvalidEmail) {
                    Row {
                        Text("Invalid email")
                    }
                } else {
                    Unit
                }
            },
        )
        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = user?.address.orEmpty(),
            placeholder = stringResource(id = R.string.address),
            maxLines = 3,
            onValueChange = {
                onAddressChanged(it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UpdateProfileScreenContentFilledPreview() {
    UpdateProfileScreenContent(user = DemoUser)
}

@Preview(showBackground = true)
@Composable
private fun UpdateProfileScreenContentEmptyPreview() {
    UpdateProfileScreenContent(
        user = DemoUser.copy(name = "", email = "", phone = "", address = ""),
        isInvalidEmail = true,
    )
}
