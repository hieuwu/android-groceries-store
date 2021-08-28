package com.hieuwu.groceriesstore.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCartBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.presentation.viewmodels.CartViewModel
import com.hieuwu.groceriesstore.presentation.viewmodels.factory.CartViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
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

        binding.cartDetailRecyclerview.adapter =
            LineListItemAdapter(LineListItemAdapter.OnClickListener {
                viewModel.displayPropertyDetails(it)
            })

        binding.lifecycleOwner = this
        return binding.root
    }

}