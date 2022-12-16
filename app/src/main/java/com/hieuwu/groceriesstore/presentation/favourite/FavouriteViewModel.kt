package com.hieuwu.groceriesstore.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hieuwu.groceriesstore.domain.models.RecipeModel
import com.hieuwu.groceriesstore.data.repository.RecipeRepository
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ObservableViewModel() {

    private var _recipesList: MutableLiveData<List<RecipeModel>> = recipeRepository.getFromLocal()
            as MutableLiveData<List<RecipeModel>>
    val recipesList: LiveData<List<RecipeModel>>
        get() = _recipesList
}
