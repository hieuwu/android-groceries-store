package com.hieuwu.groceriesstore.presentation.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSigninBinding
import timber.log.Timber


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSigninBinding>(
            inflater, R.layout.fragment_signin, container, false
        )

        auth = Firebase.auth

        val viewModelFactory = ViewModelFactory(null, null)
        val signInViewModel = ViewModelProvider(this, viewModelFactory)
            .get(SignInViewModel::class.java)
        binding.signInViewModel = signInViewModel

        binding.lifecycleOwner = this

        binding.signUpTextview.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signinButton.setOnClickListener {
            //If sign in successful, go back
            createAccount(signInViewModel.email!!, signInViewModel.password!!)
//            val i = Intent(this.context, MainActivity::class.java)
//            startActivity(i)
        }

        return binding.root
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                Timber.d(task.exception)
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_LONG).show()
                }

            }

    }

}
