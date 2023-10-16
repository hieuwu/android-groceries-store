package com.hieuwu.groceriesstore.presentation.delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.utilities.KeyData

class DeliveryFragment : Fragment() {
  
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                DeliveryScreen(
                    onNavigateBack = { findNavController().navigateUp() },
                    onUpdateClick = { delivery ->
                        parentFragmentManager.setFragmentResult(
                            KeyData.RESULT_KEY, bundleOf(
                                KeyData.NAME_KEY to delivery.name,
                                KeyData.PHONE_KEY to delivery.phoneNumber,
                                KeyData.STREET_KEY to delivery.street,
                                KeyData.WARD_KEY to delivery.district,
                                KeyData.CITY_KEY to delivery.province
                            )
                        )
                        findNavController().popBackStack()
                    }
                )
            }
        }
    }
}
