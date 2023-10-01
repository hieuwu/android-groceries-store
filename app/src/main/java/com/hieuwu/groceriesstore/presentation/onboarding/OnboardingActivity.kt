package com.hieuwu.groceriesstore.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.hieuwu.groceriesstore.MainActivity
import com.hieuwu.groceriesstore.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContent {
            OnboardingScreen(navigateToMainInitialScreen = ::navigateToMainInitialScreen)
        }
        setEvenListener()
    }

    private fun navigateToMainInitialScreen() {
        val intent = Intent(this.applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
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
    }
}
