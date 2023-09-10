package com.hieuwu.groceriesstore.presentation.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ShopScreen(
                    navigateToProductDetails = { id -> navigateToProductDetail(id)
                    }
                )
            }
        }
    }

    private fun navigateToProductDetail(productId: String) {
        val direction = ShopFragmentDirections.actionShopFragmentToProductDetailFragment(
            productId
        )
        findNavController().navigate(direction)
    }

}
