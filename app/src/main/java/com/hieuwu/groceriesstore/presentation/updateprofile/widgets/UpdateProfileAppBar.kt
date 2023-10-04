package com.hieuwu.groceriesstore.presentation.updateprofile.widgets

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.hieuwu.groceriesstore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfileAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_detail_back),
                    tint = Color.Unspecified,
                    contentDescription = null,
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.update_profile),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            TextButton(onClick = onSaveClick) {
                Text(
                    text = stringResource(R.string.save).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.colorPrimary)
        ),
    )
}

@Preview
@Composable
private fun UpdateProfileAppBarPreview() {
    UpdateProfileAppBar(
        onBackClick = {},
        onSaveClick = {},
    )
}
