package com.hieuwu.groceriesstore.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCheckOutBinding
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.presentation.viewmodels.CheckOutViewModel
import com.hieuwu.groceriesstore.presentation.viewmodels.factory.CheckOutViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CheckOutFragment : Fragment() {
    private lateinit var binding: FragmentCheckOutBinding

    @Inject
    lateinit var orderRepository: OrderRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<FragmentCheckOutBinding>(
            inflater,
            R.layout.fragment_check_out,
            container,
            false
        )
        val args = CheckOutFragmentArgs.fromBundle(arguments as Bundle)

        val viewModelFactory =
            CheckOutViewModelFactory(args.orderId, orderRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CheckOutViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = LineListItemAdapter(
            LineListItemAdapter.OnClickListener(
                minusListener = { (_) -> },
                plusListener = { (_) -> }
            )
        )
        binding.cartDetailRecyclerview.adapter = adapter

        viewModel.order.observe(viewLifecycleOwner, {

        })

        return binding.root
    }
}