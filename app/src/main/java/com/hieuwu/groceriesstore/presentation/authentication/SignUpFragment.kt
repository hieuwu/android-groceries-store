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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hieuwu.groceriesstore.LoadingDialog
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentSignUpBinding
import timber.log.Timber

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var database: DatabaseReference

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

        val viewModelFactory = ViewModelFactory(null, null)
        val signUpViewModel = ViewModelProvider(this, viewModelFactory)
            .get(SignUpViewModel::class.java)
        binding.signUpViewModel = signUpViewModel
        signUpViewModel.user?.observe(viewLifecycleOwner) {
            if (it != null) {
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

        binding.lifecycleOwner = this

        binding.signupBtn.setOnClickListener {
            //If sign in successful, go back
            createAccount(
                signUpViewModel.email!!,
                signUpViewModel.password!!,
                signUpViewModel.name!!
            )
        }


        return binding.root
    }

    private fun createAccount(email: String, password: String, name: String) {
        loadingDialog.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                Timber.d(task.exception)
                if (task.isSuccessful) {
                    // Create account success, update UI with the signed-in user's information
                    val userId = auth.currentUser!!.uid

                    val newUser = hashMapOf(
                        "name" to name,
                        "email" to email,
                    )
                    val db = Firebase.firestore
                    db.collection("users").document(userId)
                        .set(newUser)
                        .addOnSuccessListener {
                            Toast.makeText(
                                context, "Authentication successfully.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        .addOnFailureListener { e -> Timber.d("Error writing document%s", e) }
                    //Save user information to database
                    loadingDialog.dismiss()
                    activity?.finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_LONG
                    ).show()
                    loadingDialog.dismiss()
                }

            }.addOnFailureListener { Exception -> Timber.d(Exception) }

    }

}