package com.hieuwu.groceriesstore.presentation.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentShopBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding

    @Inject
    lateinit var orderRepository: OrderRepository


    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentShopBinding>(
            inflater, R.layout.fragment_shop, container, false
        )

        val viewModelFactory = ShopViewModelFactory(productRepository, orderRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ShopViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpRecyclerView(viewModel)

        viewModel.navigateToSelectedProperty.observe(this.viewLifecycleOwner, {

            if (null != it) {
                val direction = ShopFragmentDirections.actionShopFragmentToProductDetailFragment(
                    it.id
                )
                findNavController().navigate(direction)
                viewModel.displayPropertyDetailsComplete()
            }
        })


        return binding.root
    }

    private fun showSnackBar(productName: String?) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "Added $productName",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setUpRecyclerView(viewModel: ShopViewModel) {
        binding.exclusiveOfferRecyclerview.adapter =
            GridListItemAdapter(
                GridListItemAdapter.OnClickListener(
                    clickListener = {
                        viewModel.displayPropertyDetails(it)
                    },
                    addToCartListener = {
                        viewModel.addToCart(it)
                        showSnackBar(it.name)
                    },
                )
            )

        binding.exclusiveOfferRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.bestSellingRecyclerview.adapter =
            GridListItemAdapter(
                GridListItemAdapter.OnClickListener(
                    clickListener = { viewModel.displayPropertyDetails(it) },
                    addToCartListener = {
                        viewModel.addToCart(it)
                        showSnackBar(it.name)
                    },
                )
            )

        binding.bestSellingRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.recommendedRecyclerview.adapter =
            GridListItemAdapter(
                GridListItemAdapter.OnClickListener(
                    clickListener = { viewModel.displayPropertyDetails(it) },
                    addToCartListener = {
                        viewModel.addToCart(it)
                        showSnackBar(it.name)
                    },
                )
            )

        binding.recommendedRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}