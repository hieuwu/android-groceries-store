package com.hieuwu.groceriesstore.presentation.cart

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCartBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.OrderRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.SwipeToDeleteCallback
import com.hieuwu.groceriesstore.presentation.shop.ShopFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCartBinding

    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository

    @Inject
    lateinit var orderRepository: OrderRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCartBinding>(
            inflater, R.layout.fragment_cart, container, false
        )

        val viewModelFactory = CartViewModelFactory(productRepository, orderRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CartViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this
        val adapter = LineListItemAdapter(
            LineListItemAdapter.OnClickListener(
                minusListener = { viewModel.decreaseQty(it) },
                plusListener = { viewModel.increaseQty(it) },
            ),requireContext()
        )

        //Setup recyclerview
        val itemTouchHelper =
            ItemTouchHelper(SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(binding.cartDetailRecyclerview);
        binding.cartDetailRecyclerview.adapter = adapter


        viewModel.totalPrice.observe(viewLifecycleOwner, {
            binding.total.text = it.toString()
        })

        viewModel.order.observe(viewLifecycleOwner, {
            if (it != null) {
                viewModel.sumPrice()
            }
        })

        binding.checkoutButton.setOnClickListener {
            val direction =
                ShopFragmentDirections.actionShopFragmentToCheckOutFragment2(
                    viewModel.order.value?.order?.id as String
                )
            this.findNavController().navigate(direction)
            dismiss();
        }

        registerForContextMenu(binding.cartDetailRecyclerview)
        return binding.root
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = MenuInflater(context)
        inflater.inflate(R.menu.line_item_context_menu, menu)

    }


}

