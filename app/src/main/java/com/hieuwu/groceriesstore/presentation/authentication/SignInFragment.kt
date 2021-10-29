package com.hieuwu.groceriesstore.presentation.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSigninBinding
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    private lateinit var auth: FirebaseAuth


    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSigninBinding>(
            inflater, R.layout.fragment_signin, container, false
        )

        auth = FirebaseAuth.getInstance()

        val viewModelFactory = ViewModelFactory(userRepository)
        val signInViewModel = ViewModelProvider(this, viewModelFactory)
            .get(SignInViewModel::class.java)
        binding.signInViewModel = signInViewModel

        binding.lifecycleOwner = this

        binding.signUpTextview.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        signInViewModel.isSignUpSuccessful.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it == true) activity?.finish()
            }
        }

        binding.signinButton.setOnClickListener {
            signInViewModel.signIn(signInViewModel.email!!, signInViewModel.password!!)

        }

        return binding.root
    }


}
