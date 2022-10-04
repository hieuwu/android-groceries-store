package com.hieuwu.groceriesstore.presentation.cart

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCartBinding
import com.hieuwu.groceriesstore.presentation.adapters.LineListItemAdapter
import com.hieuwu.groceriesstore.presentation.adapters.SwipeToDeleteCallback
import com.hieuwu.groceriesstore.presentation.shop.ShopFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCartBinding>(
            inflater, R.layout.fragment_cart, container, false
        )
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

        // Setup recyclerview
        val itemTouchHelper =
            ItemTouchHelper(SwipeToDeleteCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.cartDetailRecyclerview)
        binding.cartDetailRecyclerview.adapter = adapter

        binding.checkoutButton.setOnClickListener {
            val direction =
                ShopFragmentDirections.actionShopFragmentToCheckOutFragment(
                    viewModel.order.value?.id as String
                )
            this.findNavController().navigate(direction)
            dismiss()
        }
    }
    private fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.order.flowWithLifecycle(lifecycle).collect {}
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
