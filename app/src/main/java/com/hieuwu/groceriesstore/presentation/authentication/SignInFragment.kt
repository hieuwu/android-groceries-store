package com.hieuwu.groceriesstore.presentation.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSigninBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSigninBinding>(
            inflater, R.layout.fragment_signin, container, false
        )

        auth = FirebaseAuth.getInstance()

        binding.signInViewModel = viewModel
        binding.lifecycleOwner = this

        setObserver()
        setEventListener()

        return binding.root
    }

    private fun setObserver() {
        viewModel.isSignUpSuccessful.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it == true) activity?.finish()
            }
        }
    }

    private fun setEventListener() {
        binding.signUpTextview.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signinButton.setOnClickListener {
            viewModel.signIn(viewModel.email!!, viewModel.password!!)
        }
    }
}
