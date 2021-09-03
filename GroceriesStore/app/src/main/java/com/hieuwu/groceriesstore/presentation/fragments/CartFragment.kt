package com.hieuwu.groceriesstore.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCartBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.presentation.viewmodels.CartViewModel
import com.hieuwu.groceriesstore.presentation.viewmodels.factory.CartViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCartBinding

    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCartBinding>(
            inflater, R.layout.fragment_cart, container, false
        )

        val viewModelFactory = CartViewModelFactory(productRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CartViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        var onClickListener = LineListItemAdapter.OnClickListener(minusListener = { minusClick() },
            plusListener = { plusClick() })
        onClickListener.minusListener = { minusClick() }
        onClickListener.plusListener = { plusClick() }

        binding.cartDetailRecyclerview.adapter =
            LineListItemAdapter(onClickListener)


        viewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            binding.total.text = it.toString()
        })

        viewModel.lineItemList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                viewModel.sumPrice()
            }
        })

        return binding.root
    }

    private fun minusClick() {
        Timber.d("Minus Clicked")
    }

    private fun plusClick() {
        Timber.d("Plus Clicked")
    }

}