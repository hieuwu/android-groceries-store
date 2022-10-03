package com.hieuwu.groceriesstore.presentation.checkout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCheckOutBinding
import com.hieuwu.groceriesstore.domain.usecases.CreateOrderUseCase
import com.hieuwu.groceriesstore.presentation.AuthActivity
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.utilities.KeyData
import com.hieuwu.groceriesstore.utilities.showMessageSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CheckOutFragment : Fragment() {
    private lateinit var binding: FragmentCheckOutBinding
    private lateinit var viewModel: CheckOutViewModel

    @Inject
    lateinit var createOrderUseCase: CreateOrderUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
            CheckOutViewModelFactory(args.orderId, createOrderUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CheckOutViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = LineListItemAdapter(
            LineListItemAdapter.OnClickListener(),
            requireContext()
        )
        binding.cartDetailRecyclerview.adapter = adapter
        setObserver()
        setEventListener()

        return binding.root
    }

    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.order.collect {
                        if (it != null) viewModel.sumPrice()
                    }
                }
                launch {
                    viewModel.totalPrice.collect {}
                }
                launch {
                    viewModel.isOrderSentSuccessful.collect {
                        when (it) {
                            true -> openSuccessDialog()
                            false -> showMessageSnackBar(getString(R.string.order_created_failed))
                            else -> {} // before send order
                        }
                    }
                }
            }
        }
    }

    private fun openSuccessDialog() {
        val dialog = CheckOutSuccess()
        dialog.setOnDismissListener { findNavController().navigateUp() }
        dialog.show(requireActivity().supportFragmentManager, getString(R.string.order_success_dialog))
    }

    private fun setEventListener() {
        binding.deliveryEditBtn.setOnClickListener {
            findNavController().navigate(R.id.action_checkOutFragment_to_deliveryFragment)
        }

        binding.confirmOrderBtn.setOnClickListener {
            if (viewModel.user.value != null) {
                viewModel.sendOrder()
            } else {
                val i = Intent(context, AuthActivity::class.java)
                startActivity(i)
            }
        }

        parentFragmentManager.setFragmentResultListener(
            KeyData.RESULT_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            val name = bundle.getString(KeyData.NAME_KEY)
            val phone = bundle.getString(KeyData.PHONE_KEY)
            val street = bundle.getString(KeyData.STREET_KEY)
            val ward = bundle.getString(KeyData.WARD_KEY)
            val city = bundle.getString(KeyData.CITY_KEY)
            binding.deliveryContent.text = "$name\n$phone\n$street, $ward, $city"
        }
    }
}
