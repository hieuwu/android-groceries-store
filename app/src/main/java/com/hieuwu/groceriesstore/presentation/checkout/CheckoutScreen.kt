package com.hieuwu.groceriesstore.presentation.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.checkout.composables.CartDetailList
import com.hieuwu.groceriesstore.presentation.checkout.composables.CheckoutSuccessDialog
import com.hieuwu.groceriesstore.presentation.core.widgets.PrimaryButton

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckOutViewModel = hiltViewModel(),
    address: String = "",
    onSuccessDialogDismiss: () -> Unit,
    onDeliveryEdit: () -> Unit = {},
    onPaymentMethodEdit: () -> Unit = {},
    openAuthActivity: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val user = viewModel.user.collectAsState().value
    val order = viewModel.order.collectAsState().value
    val totalPrice = viewModel.totalPrice.collectAsState().value
    val isOrderSentSuccessful = viewModel.isOrderSentSuccessful.collectAsState().value

    var showSuccessDialog by remember {
        mutableStateOf<Boolean?>(null)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        val orderFailedMsg = stringResource(R.string.order_created_failed)

        LaunchedEffect(key1 = isOrderSentSuccessful) {
            when (isOrderSentSuccessful) {
                true -> showSuccessDialog = true
                false -> snackbarHostState.showSnackbar(orderFailedMsg)
                else -> {} // before send order
            }
        }

        LaunchedEffect(key1 = order) {
            order?.let {
                viewModel.sumPrice()
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(state = scrollState)
        ) {

            if (showSuccessDialog == true) {
                CheckoutSuccessDialog {
                    onSuccessDialogDismiss()
                    showSuccessDialog = false
                }
            }

            // RecyclerView for cart details
            CartDetailList(
                cartItems = order?.lineItemList ?: listOf(),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            DeliverySection(
                address = address,
                modifier = Modifier.fillMaxWidth(),
                onEdit = onDeliveryEdit
            )

            PaymentMethodSection(
                modifier = Modifier.fillMaxWidth(),
                onEdit = onPaymentMethodEdit
            )

            ShippingFeeSection(
                modifier = Modifier.fillMaxWidth()
            )

            TotalSection(
                totalPrice = totalPrice,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                onClick = {
                    user?.let {
                        viewModel.sendOrder()
                    } ?: openAuthActivity()
                },
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.confirm_order),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun DeliverySection(
    address: String,
    modifier: Modifier = Modifier,
    onEdit: () -> Unit
) {
    Column {
        Row(
            modifier = modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.delivery),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = stringResource(id = R.string.edit),
                color = colorResource(id = R.color.colorPrimary),
                modifier = Modifier.clickable { onEdit() }
            )
        }

        Text(
            text = address,
            fontSize = 16.sp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun PaymentMethodSection(
    modifier: Modifier = Modifier,
    onEdit: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.payment_method),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            text = stringResource(id = R.string.edit),
            color = colorResource(id = R.color.colorPrimary),
            modifier = Modifier.clickable { onEdit() }
        )
    }
}

@Composable
fun ShippingFeeSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.shipping_fee),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            text = "12.3",
            color = colorResource(id = R.color.colorPrimary)
        )
    }
}

@Composable
fun TotalSection(
    totalPrice: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.total),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            text = "$totalPrice $",
            color = colorResource(id = R.color.colorPrimary)
        )
    }
}
