package com.hieuwu.groceriesstore.presentation.favourite.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.domain.models.RecipeModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    recipe: RecipeModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp)
        ) {
            GlideImage(
                contentScale = ContentScale.Crop,
                model = recipe.image,
                contentDescription = null,
                modifier = modifier.weight(1f)
            )
            Text(
                modifier = modifier.padding(vertical = 6.dp),
                text = recipe.name,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}