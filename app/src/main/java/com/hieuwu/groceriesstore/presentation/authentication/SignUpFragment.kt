package com.hieuwu.groceriesstore.presentation.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hieuwu.groceriesstore.LoadingDialog
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSignUpBinding
import com.hieuwu.groceriesstore.utilities.showMessageSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater, R.layout.fragment_sign_up, container, false
        )
        loadingDialog = LoadingDialog(requireActivity())

        binding.signUpViewModel = viewModel
        binding.lifecycleOwner = this

        setObserver()
        setEventListener()

        return binding.root
    }

    private fun setObserver() {
        viewModel.isSignUpSuccessful?.observe(viewLifecycleOwner) {
            loadingDialog.show()
            if (it != null) {

                if (it == true) showMessageSnackBar(getString(R.string.authentication_successfully))
                else showMessageSnackBar(getString(R.string.authentication_failed))

                loadingDialog.dismiss()
            }
        }
    }

    private fun setEventListener() {
        binding.signupBtn.setOnClickListener {
            viewModel.createAccount()
        }
    }
}
