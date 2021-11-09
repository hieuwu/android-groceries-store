package com.hieuwu.groceriesstore.presentation.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentShopBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.ViewPagerAdapter
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

        val nonactiveDot =
            ContextCompat.getDrawable(requireContext(), R.drawable.non_active_dot_shape)
        val activeDot = ContextCompat.getDrawable(requireContext(), R.drawable.active_dot_shape)

        val viewPagerAdapter = ViewPagerAdapter(requireContext())
        binding.viewPager.adapter = viewPagerAdapter

        val dotCount = viewPagerAdapter.count
        val sliderDotspanel = binding.sliderDots
        val dots: Array<ImageView> = Array(dotCount){ ImageView(requireContext())}
        for (i in 0 until dotCount) {
            dots[i].setImageDrawable(nonactiveDot)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(activeDot)
        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotCount) {
                    dots[i].setImageDrawable(nonactiveDot);
                }
                dots[position].setImageDrawable(activeDot);

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })



        viewModel.CurrentCart.observe(viewLifecycleOwner, {})

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