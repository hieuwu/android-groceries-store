package com.hieuwu.groceriesstore.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentProductListBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    lateinit var binding: FragmentProductListBinding

    @Inject
    lateinit var orderRepository: OrderRepository

    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProductListBinding>(
            inflater, R.layout.fragment_product_list, container, false
        )
        val viewModelFactory = ProductListViewModelFactory(productRepository, orderRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ProductListViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpRecyclerView(viewModel)
        viewModel.navigateToSelectedProperty.observe(this.viewLifecycleOwner, {
            if (null != it) {
                val direction = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                    it.id
                )
                findNavController().navigate(direction)
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }

    private fun setUpRecyclerView(viewModel: ProductListViewModel) {
        binding.productRecyclerview.adapter =
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
    }

    private fun showSnackBar(productName: String?) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "Added $productName",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}