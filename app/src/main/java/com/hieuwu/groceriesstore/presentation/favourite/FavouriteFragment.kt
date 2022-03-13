package com.hieuwu.groceriesstore.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentFavouriteBinding
import com.hieuwu.groceriesstore.domain.repository.RecipeRepository
import com.hieuwu.groceriesstore.presentation.adapters.RecipeItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

    @Inject
    lateinit var recipeRepository: RecipeRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFavouriteBinding>(
            inflater, R.layout.fragment_favourite, container, false
        )
        val viewModelFactory = FavouriteViewModelFactory(recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavouriteViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recipesRecyclerview.adapter =
            RecipeItemAdapter(RecipeItemAdapter.OnClickListener())

        viewModel.recipesList.observe(viewLifecycleOwner) {}
        return binding.root
    }
}