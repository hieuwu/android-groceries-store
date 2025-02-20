package com.hieuwu.groceriesstore.presentation.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController



class ExploreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                ExploreScreen(
                    navigateToProductList = { category ->
                        navigateToProductList(category.id, category.name!!)
                    },
                    navigateToProductDetail = { product ->
                         navigateToProductDetail(product.id)
                    }
                )
            }
        }
    }

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
}
