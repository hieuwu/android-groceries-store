package com.hieuwu.groceriesstore.presentation.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.LoadingDialog
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSignUpBinding
import com.hieuwu.groceriesstore.domain.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var database: DatabaseReference

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater, R.layout.fragment_sign_up, container, false
        )
        loadingDialog = LoadingDialog(requireActivity())

        val viewModelFactory = ViewModelFactory(userRepository)
        val signUpViewModel = ViewModelProvider(this, viewModelFactory)
            .get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel
        signUpViewModel.isSignUpSuccessful?.observe(viewLifecycleOwner) {
            loadingDialog.show()
            if (it != null) {
                if (it == true) {
                    Toast.makeText(
                        context, "Authentication successfully.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_LONG
                    ).show()
                    loadingDialog.dismiss()
                }
            }


        }

        binding.lifecycleOwner = this
        binding.signupBtn.setOnClickListener {
            //If sign in successful, go back
            signUpViewModel.createAccount()
        }

        return binding.root
    }

}