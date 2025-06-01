package com.hieuwu.groceriesstore.presentation.productlist.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListAppBar(
    title: String,
    navigateUp: () -> Unit,
    showFilter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_navigate_back)
                )
            }
        },
        actions = {
            IconButton(
                onClick = showFilter,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_filter_list_36),
                    contentDescription = stringResource(id = R.string.cd_save),
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.colorPrimary),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}

@Preview
@Composable
private fun ProductListAppBarPreview() {
    ProductListAppBar(title = "Delivery", navigateUp = {}, showFilter = {})
}
