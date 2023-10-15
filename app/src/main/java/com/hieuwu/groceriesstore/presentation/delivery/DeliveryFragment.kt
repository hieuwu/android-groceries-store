package com.hieuwu.groceriesstore.presentation.delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentDeliveryBinding
import com.hieuwu.groceriesstore.utilities.KeyData

class DeliveryFragment : Fragment() {
    private lateinit var binding: FragmentDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_delivery,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.updateDeliveryBtn.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                KeyData.RESULT_KEY, bundleOf(
                    KeyData.NAME_KEY to binding.receiverName.text.toString(),
                    KeyData.PHONE_KEY to binding.receiverPhone.text.toString(),
                    KeyData.STREET_KEY to binding.addressStreet.text.toString(),
                    KeyData.WARD_KEY to binding.addressWard.text.toString(),
                    KeyData.CITY_KEY to binding.addressCity.text.toString()
                )
            )
            findNavController().popBackStack()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}
