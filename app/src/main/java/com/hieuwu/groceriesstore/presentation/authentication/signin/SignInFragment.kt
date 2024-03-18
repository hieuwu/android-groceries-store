package com.hieuwu.groceriesstore.presentation.authentication.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.hieuwu.groceriesstore.R
import dagger.hilt.android.AndroidEntryPoint

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
                    modifier = Modifier.fillMaxSize(),
                    onSignUpClick = {
                        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
                    },
                    onSignInSuccess = {
                        requireActivity().finish()
                    }
                )
            }
        }
    }
}
