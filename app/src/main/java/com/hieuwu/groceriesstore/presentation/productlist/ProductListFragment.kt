package com.hieuwu.groceriesstore.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentProductListBinding
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import com.hieuwu.groceriesstore.utilities.showMessageSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProductListBinding>(
            inflater, R.layout.fragment_product_list, container, false
        )

        val args = ProductListFragmentArgs.fromBundle(
            arguments as Bundle
        )

        val categoryName = args.categoryName
        binding.toolbar.title = categoryName

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpRecyclerView()
        setObserver()
        setEventListener()

        return binding.root
    }

    private fun setEventListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_filter -> {
                    showFilterDialog()
                    true
                }
                else -> false
            }
        }
    }

    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.navigateToSelectedProperty.collect {
                        if (null != it) {
                            navigateToProductDetail(it.id)
                            viewModel.displayProductDetailComplete()
                        }
                    }
                }
                launch {
                    viewModel.currentCart.collect {}
                }
                launch {
                    viewModel.productList.collect {
                        if (it.isEmpty()) {
                            binding.productRecyclerview.visibility = View.GONE
                            binding.emptyLayout.visibility = View.VISIBLE
                        } else {
                            binding.productRecyclerview.visibility = View.VISIBLE
                            binding.emptyLayout.visibility = View.GONE
                        }
                    }
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
        val bottomSheetDialogFragment = FilterFragment()
        bottomSheetDialogFragment.show(
            activity?.supportFragmentManager!!,
            bottomSheetDialogFragment.tag
        )
    }

    private fun setUpRecyclerView() {
        binding.productRecyclerview.adapter =
            GridListItemAdapter(
                GridListItemAdapter.OnClickListener(
                    clickListener = {
                        viewModel.displayProductDetail(it)
                    },
                    addToCartListener = {
                        viewModel.addToCart(it)
                        showMessageSnackBar("Added ${it.name}")
                    }
                )
            )
    }
}
