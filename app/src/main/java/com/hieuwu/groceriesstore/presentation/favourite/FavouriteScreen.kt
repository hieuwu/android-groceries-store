package com.hieuwu.groceriesstore.presentation.favourite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

import com.hieuwu.groceriesstore.presentation.favourite.composables.RecipeItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = koinViewModel()
) {
    val recipes = viewModel.recipesList.observeAsState().value
    LazyColumn {
        items(recipes!!) { item ->
            RecipeItem(recipe = item)

        }
    }
}