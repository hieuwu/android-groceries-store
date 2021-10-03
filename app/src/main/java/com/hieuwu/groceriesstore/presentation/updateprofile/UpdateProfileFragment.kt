package com.hieuwu.groceriesstore.presentation.updateprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentUpdateProfileBinding


class UpdateProfileFragment : Fragment() {

    lateinit var binding: FragmentUpdateProfileBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentUpdateProfileBinding>(
            inflater,
            R.layout.fragment_update_profile,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        
        return binding.root
    }

}