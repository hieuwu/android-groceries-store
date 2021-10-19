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
import com.hieuwu.groceriesstore.LoadingDialog
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSignUpBinding
import timber.log.Timber

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: FragmentSignUpBinding;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()


        binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater, R.layout.fragment_sign_up, container, false
        )
        loadingDialog = LoadingDialog(requireActivity())

        val viewModelFactory = ViewModelFactory(null, null)
        val signUpViewModel = ViewModelProvider(this, viewModelFactory)
            .get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel

        binding.lifecycleOwner = this

        binding.signupBtn.setOnClickListener {
            //If sign in successful, go back
            createAccount(signUpViewModel.email!!, signUpViewModel.password!!)
//            val i = Intent(this.context, MainActivity::class.java)
//            startActivity(i)
        }


        return binding.root
    }

    private fun createAccount(email: String, password: String) {
        loadingDialog.showLoadingDialog()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                Timber.d(task.exception)
                if (task.isSuccessful) {
                    // Create account success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }.addOnFailureListener { Exception -> Timber.d(Exception) }
    }

}