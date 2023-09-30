package com.hieuwu.groceriesstore.presentation.authentication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSigninBinding
import com.hieuwu.groceriesstore.utilities.showMessageSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                SignInScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
//
//    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
//    private fun setObserver() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    viewModel.showAccountNotExistedError.collect {
//                        showMessageSnackBar("Account is not existed")
//                    }
//                }
//
//                launch {
//                    viewModel.isSignUpSuccessful.collect {
//                        if (it == true) {
//                            activity?.finish()
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun setEventListener() {
//        with(binding) {
//            signUpTextview.setOnClickListener {
//                view?.findNavController()?.navigate(R.id.action_signInFragment_to_signUpFragment)
//            }
//            signinButton.setOnClickListener {
//                viewModel.signIn()
//            }
//        }
//    }
}
