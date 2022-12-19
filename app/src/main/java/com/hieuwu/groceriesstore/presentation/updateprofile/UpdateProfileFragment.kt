package com.hieuwu.groceriesstore.presentation.updateprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentUpdateProfileBinding
import com.hieuwu.groceriesstore.utilities.showMessageSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateProfileFragment : Fragment() {

    lateinit var binding: FragmentUpdateProfileBinding
    private val viewModel: UpdateProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentUpdateProfileBinding>(
            inflater,
            R.layout.fragment_update_profile,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setObserver()
        setEventListener()

        return binding.root
    }

    private fun setEventListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> {
                    // Update user data to backend
                    viewModel.updateUserProfile()
                    true
                }
                else -> false
            }
        }
    }

    private fun setObserver() {
        viewModel.isDoneUpdate.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it == true) {
                    R.string.add_to_basket
                    showMessageSnackBar(getString(R.string.update_profile_successfully))
                } else {
                    showMessageSnackBar(getString(R.string.update_profile_failed))
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.user.collect{
                        if (it != null) {
                            viewModel.email = it.email
                            viewModel.name = it.name
                            viewModel.phoneNumber = it.phone
                            viewModel.address = it.address
                        }
                    }
                }
            }
        }
    }
}
