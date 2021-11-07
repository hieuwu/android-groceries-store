package com.hieuwu.groceriesstore.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentAccountBinding
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import com.hieuwu.groceriesstore.presentation.updateprofile.UpdateProfileViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAccountBinding>(
            inflater, R.layout.fragment_account, container, false
        )

        val viewModelFactory = UpdateProfileViewModelFactory(userRepository)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(AccountViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this
        binding.profileLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_updateProfileFragment)
        }

        binding.signoutButton.setOnClickListener {
            viewModel.signOut()
        }

        return binding.root
    }

}