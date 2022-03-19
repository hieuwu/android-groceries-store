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
import com.hieuwu.groceriesstore.domain.usecases.UpdateCartItemUseCase
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.SwipeToDeleteCallback
import com.hieuwu.groceriesstore.presentation.shop.ShopFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    @Inject
    lateinit var updateCartItemUseCase: UpdateCartItemUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCartBinding>(
            inflater, R.layout.fragment_cart, container, false
        )
        val viewModelFactory = CartViewModelFactory(updateCartItemUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CartViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setEventListener()
        setObserver()
        registerForContextMenu(binding.cartDetailRecyclerview)
        return binding.root
    }

    private fun setEventListener() {
        val adapter = LineListItemAdapter(
            LineListItemAdapter.OnClickListener(
                minusListener = { viewModel.decreaseQty(it) },
                plusListener = { viewModel.increaseQty(it) },
                removeListener = { viewModel.removeItem(it) }
            ), requireContext()
        )

        //Setup recyclerview
        val itemTouchHelper =
            ItemTouchHelper(SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(binding.cartDetailRecyclerview);
        binding.cartDetailRecyclerview.adapter = adapter

        binding.checkoutButton.setOnClickListener {
            val direction =
                ShopFragmentDirections.actionShopFragmentToCheckOutFragment(
                    viewModel.order.value?.id as String
                )
            this.findNavController().navigate(direction)
            dismiss();
        }

    }
    private fun setObserver() {
        viewModel.order.observe(viewLifecycleOwner) {
//            if (it != null) {
//                if (it.lineItemList.size > 0) {
//                    binding.cartEmptyLayout.visibility = View.GONE
//                    binding.cartDetailLayout.visibility = View.VISIBLE
//                } else {
//                    binding.cartEmptyLayout.visibility = View.VISIBLE
//                    binding.cartDetailLayout.visibility = View.GONE
//                }
//            } else {
//                binding.cartEmptyLayout.visibility = View.VISIBLE
//                binding.cartDetailLayout.visibility = View.GONE
//            }
        }
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

