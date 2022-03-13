package com.hieuwu.groceriesstore.presentation.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.hieuwu.groceriesstore.MainActivity
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.ActivityOnboardingBinding
import com.hieuwu.groceriesstore.domain.usecases.RefreshAppDataUseCase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding

    private lateinit var viewModel: OnboardingViewModel
    private lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var refreshAppDataUseCase: RefreshAppDataUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        sharedPreferences =
            getSharedPreferences(getString(R.string.sync_status_pref_name), Context.MODE_PRIVATE)

        val isSyncedSuccessful =
            sharedPreferences.getBoolean(getString(R.string.sync_success), false)

        if (isSyncedSuccessful) navigateToMainInitialScreen()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        val viewModelFactory =
            OnboardingViewModelFactory(refreshAppDataUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OnboardingViewModel::class.java)
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
        viewModel.isSyncedSuccessful.observe(this) {
            if (it!!) {
                if (it == true) {
                    with(sharedPreferences.edit()) {
                        putBoolean(getString(R.string.sync_success), true)
                        apply()
                    }
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}