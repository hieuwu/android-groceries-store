package com.hieuwu.groceriesstore.presentation.updateprofile.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.UserModel
import com.hieuwu.groceriesstore.presentation.account.DemoUser

@Composable
fun UpdateProfileScreenContent(
    modifier: Modifier = Modifier,
    user: UserModel?,
    onNameChanged: (String) -> Unit = {},
    onPhoneChanged: (String) -> Unit = {},
    onEmailChanged: (String) -> Unit = {},
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

        var name by remember { mutableStateOf(user?.name) }
        var phone by remember { mutableStateOf(user?.phone) }
        var email by remember { mutableStateOf(user?.email) }
        var address by remember { mutableStateOf(user?.address) }

        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = name.orEmpty(),
            placeholder = stringResource(id = R.string.name),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            onValueChange = {
                onNameChanged(it)
                name = it
            }
        )
        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = phone.orEmpty(),
            placeholder = stringResource(id = R.string.phone_number),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            onValueChange = {
                onPhoneChanged(it)
                phone = it
            }
        )
        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = email.orEmpty(),
            placeholder = stringResource(id = R.string.email),
            singleLine = true,
            onValueChange = {
                onEmailChanged(it)
                email = it
            }
        )
        UpdateProfileEditField(
            modifier = Modifier.fillMaxWidth(),
            value = address.orEmpty(),
            placeholder = stringResource(id = R.string.address),
            maxLines = 3,
            onValueChange = {
                onAddressChanged(it)
                address = it
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
        user = DemoUser.copy(name = "", email = "", phone = "", address = "")
    )
}
