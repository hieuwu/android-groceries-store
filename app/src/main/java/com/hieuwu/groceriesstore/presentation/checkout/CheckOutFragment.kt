package com.hieuwu.groceriesstore.presentation.checkout

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCheckOutBinding
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.presentation.AuthActivity
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.utilities.KeyData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class CheckOutFragment : Fragment() {
    private lateinit var binding: FragmentCheckOutBinding

    @Inject
    lateinit var orderRepository: OrderRepository

    @Inject
    lateinit var userRepository: UserRepository

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

        val args = CheckOutFragmentArgs.fromBundle(
            arguments as Bundle
        )

        val viewModelFactory =
            CheckOutViewModelFactory(args.orderId, orderRepository, userRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CheckOutViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = LineListItemAdapter(
            LineListItemAdapter.OnClickListener(
                minusListener = {},
                plusListener = {},
                removeListener = {}),
            requireContext()
        )
        binding.cartDetailRecyclerview.adapter = adapter

        viewModel.order.observe(viewLifecycleOwner, {
            if (it != null) viewModel.sumPrice()
        })

        viewModel.totalPrice.observe(viewLifecycleOwner, {})

        binding.deliveryEditBtn.setOnClickListener {
            findNavController().navigate(R.id.action_checkOutFragment_to_deliveryFragment)
        }

        binding.confirmOrderBtn.setOnClickListener {
            //Handle confirm button
            //If logged in, send data to server
            if (viewModel.user.value != null) {
                viewModel.sendOrder()
            }
            else {
                val i = Intent(context, AuthActivity::class.java)
                startActivity(i)
            }
        }

        parentFragmentManager.setFragmentResultListener(
            KeyData.RESULT_KEY,
            viewLifecycleOwner
        ) { requestKey, bundle ->
            val name = bundle.getString(KeyData.NAME_KEY)
            val phone = bundle.getString(KeyData.PHONE_KEY)
            val street = bundle.getString(KeyData.STREET_KEY)
            val ward = bundle.getString(KeyData.WARD_KEY)
            val city = bundle.getString(KeyData.CITY_KEY)
            binding.deliveryContent.text = "$name\n$phone\n$street, $ward, $city"
        }

        return binding.root
    }

}