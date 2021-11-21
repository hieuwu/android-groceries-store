package com.hieuwu.groceriesstore.presentation.favourite

import com.hieuwu.groceriesstore.domain.repository.RecipeRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ObservableViewModel() {


}