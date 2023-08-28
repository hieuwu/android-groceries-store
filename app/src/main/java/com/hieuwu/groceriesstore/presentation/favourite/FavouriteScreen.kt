package com.hieuwu.groceriesstore.presentation.favourite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hieuwu.groceriesstore.presentation.favourite.composables.RecipeItem

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = hiltViewModel()
) {
    val recipes = viewModel.recipesList.observeAsState().value
    LazyColumn {
        items(recipes!!) { item ->
            RecipeItem(recipe = item)

        }
    }
}