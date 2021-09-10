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
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentShopBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding

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

        val viewModelFactory = ShopViewModelFactory(productRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ShopViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpRecyclerView()
        binding.exclusiveOfferRecyclerview.adapter =
            GridListItemAdapter(GridListItemAdapter.OnClickListener {
                viewModel.displayPropertyDetails(it)
            })

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

    private fun setUpRecyclerView() {


        binding.exclusiveOfferRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.bestSellingRecyclerview.adapter =
            GridListItemAdapter(GridListItemAdapter.OnClickListener {
                Timber.d("on item clicked")
            })

        binding.bestSellingRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.recommendedRecyclerview.adapter =
            GridListItemAdapter(GridListItemAdapter.OnClickListener {
                Timber.d("on item clicked")
            })

        binding.recommendedRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}