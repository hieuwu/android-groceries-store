package com.hieuwu.groceriesstore.presentation.explore.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.domain.models.CategoryModel

@OptIn(
    ExperimentalGlideComposeApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryModel,
    onItemClick: () -> Unit
) {

    Card(
        onClick = onItemClick,
        modifier = modifier,
        elevation = 2.dp,
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GlideImage(
                model = category.image,
                contentDescription = category.name,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(4.dp))
            )

            Text(
                text = category.name!!,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )
        }
    }
}