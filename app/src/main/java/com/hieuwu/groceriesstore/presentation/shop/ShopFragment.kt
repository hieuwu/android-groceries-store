package com.hieuwu.groceriesstore.presentation.shop

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentShopBinding
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.ViewPagerAdapter
import com.hieuwu.groceriesstore.utilities.showMessageSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopFragment : Fragment() {

    private val viewModel: ShopViewModel by viewModels()
    private lateinit var dots: Array<ImageView>
    private lateinit var binding: FragmentShopBinding
    private lateinit var gridListItemAdapter: GridListItemAdapter
    private lateinit var nonActiveDot: Drawable
    private lateinit var activeDot: Drawable
    private var dotCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentShopBinding>(
            inflater, R.layout.fragment_shop, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        nonActiveDot =
            ContextCompat.getDrawable(requireContext(), R.drawable.non_active_dot_shape)!!
        activeDot = ContextCompat.getDrawable(requireContext(), R.drawable.active_dot_shape)!!
        setObserver()
        setUpRecyclerView()
        drawSliderDotSymbols()
        setEventListener()

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)


        return binding.root
    }

    private fun drawSliderDotSymbols() {
        val viewPagerAdapter = ViewPagerAdapter(requireContext())
        binding.viewPager.adapter = viewPagerAdapter
        dotCount = viewPagerAdapter.count
        val sliderDotspanel = binding.sliderDots
        dots = Array(dotCount) { ImageView(requireContext()) }
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 0, 10, 0)
        repeat(dotCount) {
            dots[it].setImageDrawable(nonActiveDot)
            sliderDotspanel.addView(dots[it], params)
        }
        dots[0].setImageDrawable(activeDot)
    }

    private fun setEventListener() {
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                repeat(dotCount) {
                    dots[it].setImageDrawable(nonActiveDot)
                }
                dots[position].setImageDrawable(activeDot)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.navigateToSelectedProperty.collect {
                        it?.let {
                            navigateToProductDetail(it.id)
                            viewModel.displayPropertyDetailsComplete()
                        }
                    }
                }
                launch {
                    viewModel.currentCart.collect{}
                }
            }
        }
    }

    private fun navigateToProductDetail(productId: String) {
        val direction = ShopFragmentDirections.actionShopFragmentToProductDetailFragment(
            productId
        )
        findNavController().navigate(direction)
    }

    private fun addToCart(product: ProductModel) {
        viewModel.addToCart(product)
        showMessageSnackBar("Added ${product.name}")
    }

    private fun setUpRecyclerView() {
        gridListItemAdapter = GridListItemAdapter(
            GridListItemAdapter.OnClickListener(
                clickListener = { viewModel.displayPropertyDetails(it) },
                addToCartListener = { addToCart(it) }
            )
        )
        binding.exclusiveOfferRecyclerview.adapter =
            gridListItemAdapter

        binding.exclusiveOfferRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.bestSellingRecyclerview.adapter =
            gridListItemAdapter

        binding.bestSellingRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.recommendedRecyclerview.adapter =
            gridListItemAdapter

        binding.recommendedRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}
