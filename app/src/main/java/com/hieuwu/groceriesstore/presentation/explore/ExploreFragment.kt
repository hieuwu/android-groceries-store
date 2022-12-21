package com.hieuwu.groceriesstore.presentation.explore

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentExploreBinding
import com.hieuwu.groceriesstore.presentation.adapters.CategoryItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding
    private val viewModel: ExploreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentExploreBinding>(
            inflater, R.layout.fragment_explore, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initAdapters()
        setObserver()
        setTextSearchColor()
        setEventListener()

        return binding.root
    }

    private fun initAdapters() {
        binding.productRecyclerview.adapter = GridListItemAdapter(
            createGridListItemEvent()
        )

        binding.categoryRecyclerview.adapter =
            CategoryItemAdapter(CategoryItemAdapter.OnClickListener {
                navigateToProductList(it.name!!, it.id)
            })
    }

    private fun createGridListItemEvent(): GridListItemAdapter.OnClickListener =
        GridListItemAdapter.OnClickListener(
            clickListener = {
                viewModel.displayProductDetail(it)
                viewModel.displayProductDetailComplete()
            },
            addToCartListener = {
                viewModel.addToCart(it)
            }
        )

    private fun navigateToProductList(name: String, id: String) {
        val direction =
            ExploreFragmentDirections.actionExploreFragmentToProductListFragment(name, id)
        findNavController().navigate(direction)
    }

    private fun navigateToProductDetail(productId: String) {
        val direction =
            ExploreFragmentDirections.actionExploreFragmentToProductDetailFragment(productId)
        findNavController().navigate(direction)
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun setObserver() {
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if (null != it) navigateToProductDetail(it.id)
        }
        viewModel.productList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                Timber.d("Empty")
            } else {
                binding.productRecyclerview.visibility = View.VISIBLE
                binding.animationLayout.visibility = View.GONE
                Timber.d("Has item")
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.categories.collect {}
                }

            }
        }
    }

    private fun setTextSearchColor() {
        val searchEditText =
            binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(Color.WHITE)
    }

    private fun onTextChange(text: String): Boolean {
        viewModel.searchNameChanged(text)
        return true
    }

    private fun setEventListener() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = onTextChange(query!!)
            override fun onQueryTextChange(query: String?) = onTextChange(query!!)
        })

        binding.searchView.setOnQueryTextFocusChangeListener { _, _ ->
            Timber.d("Search focused")
            // Hide category list
            // Show search result list with empty list product
            binding.categoryRecyclerview.visibility = View.GONE
        }

        binding.searchView.setOnQueryTextFocusChangeListener { _, _ ->
            Timber.d("Search focused")
            // Hide category list
            // Show search result list with empty list product
            binding.categoryRecyclerview.visibility = View.GONE
        }

        val closeSearchButton =
            binding.searchView.findViewById<AppCompatImageView>(androidx.appcompat.R.id.search_close_btn)

        closeSearchButton.setOnClickListener {
            binding.searchView.onActionViewCollapsed()
            Timber.d("Close search focused")
            // Show category list
            // Clear search result
            // Hide search result
            binding.productRecyclerview.visibility = View.GONE
            binding.categoryRecyclerview.visibility = View.VISIBLE
            binding.animationLayout.visibility = View.GONE
        }
    }
}
