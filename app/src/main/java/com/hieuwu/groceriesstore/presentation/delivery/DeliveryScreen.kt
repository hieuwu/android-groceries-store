package com.hieuwu.groceriesstore.presentation.delivery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.core.widgets.PrimaryButton
import com.hieuwu.groceriesstore.presentation.delivery.composables.Header
import com.hieuwu.groceriesstore.presentation.delivery.composables.Input

@Composable
fun DeliveryScreen(
    onNavigateBack: () -> Unit,
    onUpdateClick: (DeliveryModel) -> Unit
) {

    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.delivery))
                },
                backgroundColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.NavigateBefore,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Header(title = "Receiver")

            Input(
                value = name,
                placeholder = stringResource(id = R.string.name),
                onValueChange = {
                    name = it
                }
            )

            Input(
                value = phoneNumber,
                placeholder = stringResource(R.string.phone_number),
                onValueChange = {
                    phoneNumber = it
                }
            )

            Header(title = "Address")

            Input(
                value = street,
                placeholder = "Street",
                onValueChange = {
                    street = it
                }
            )

            Input(
                value = district,
                placeholder = "Ward/District",
                onValueChange = {
                    district = it
                }
            )

            Input(
                value = province,
                placeholder = "Province/City",
                onValueChange = {
                    province = it
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                onClick = {
                    onUpdateClick(
                        DeliveryModel(
                            name,
                            phoneNumber,
                            street,
                            district,
                            province
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.update), color = Color.White)
            }
        }
    }

}

@Preview(
    heightDp = 700
)
@Composable
fun DeliveryScreenPreview() {
    DeliveryScreen(onNavigateBack = { /*TODO*/ }, onUpdateClick = {})
}