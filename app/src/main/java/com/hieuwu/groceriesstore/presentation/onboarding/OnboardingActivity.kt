package com.hieuwu.groceriesstore.presentation.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.flowWithLifecycle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.hieuwu.groceriesstore.MainActivity
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding

    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setEvenListener()
        setObserver()
    }

    private fun navigateToMainInitialScreen() {
        val intent = Intent(this.applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.isSyncedSuccessful
                .flowWithLifecycle(lifecycle)
                .collect {
                    if (it) {
                        binding.getStartedButton.isEnabled = true
                    }
                }
        }
    }

    private fun setEvenListener() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.d("Fetching FCM registration token failed" + task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Timber.d(token)
        })

        binding.getStartedButton.setOnClickListener {
            navigateToMainInitialScreen()
        }
    }
}
