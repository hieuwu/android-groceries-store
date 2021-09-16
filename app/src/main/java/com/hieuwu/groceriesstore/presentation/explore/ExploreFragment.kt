package com.hieuwu.groceriesstore.presentation.explore

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.data.utils.ProductListMode
import com.hieuwu.groceriesstore.databinding.FragmentExploreBinding
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.presentation.adapters.CategoryItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentExploreBinding>(
            inflater, R.layout.fragment_explore, container, false
        )
        val searchEditText =
            binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(Color.WHITE)

        val viewModelFactory =
            ExploreViewModelFactory(categoryRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ExploreViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.categories.observe(viewLifecycleOwner, {})

        binding.categoryRecyclerview.adapter =
            CategoryItemAdapter(CategoryItemAdapter.OnClickListener {
                val direction =
                    ExploreFragmentDirections.actionExploreFragmentToProductListFragment(
                        it.name!!,
                        it.id
                    )
                findNavController().navigate(direction)
            })


        return binding.root
    }

}