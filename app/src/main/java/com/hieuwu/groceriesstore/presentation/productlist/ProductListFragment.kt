package com.hieuwu.groceriesstore.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController

import kotlinx.coroutines.launch


class ProductListFragment : Fragment() {
    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ProductListScreen(
                    navigateUp = { findNavController().navigateUp() },
                    showFilter = ::showFilterDialog,
                    openProductDetails = ::navigateToProductDetail,
                    viewModel = viewModel
                )
            }
            setObserver()
        }
    }

    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.currentCart.collect {}
                }
            }
        }
    }

    private fun navigateToProductDetail(id: String) {
        val direction =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(id)
        findNavController().navigate(direction)
    }

    private fun showFilterDialog() {
        // Do nothing. Add filter feature later
    }
}
