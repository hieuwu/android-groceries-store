package com.hieuwu.groceriesstore.presentation.updateprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentUpdateProfileBinding
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateProfileFragment : Fragment() {

    lateinit var binding: FragmentUpdateProfileBinding;

    @Inject
    lateinit var userRepository: UserRepository

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

        val viewModelFactory = UpdateProfileViewModelFactory(userRepository)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(UpdateProfileViewModel::class.java)
        binding.viewModel = viewModel

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.isDoneUpdate.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it == true) {
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "Update profile successfully!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else {
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "Update profile failed!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })

        viewModel.user.observe(viewLifecycleOwner, {
            if (it != null) {
                viewModel.email = it.email
                viewModel.name = it.name
            }
        })

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> {
                    //Update user data to backend
                    viewModel.updateUserProfile()
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

}