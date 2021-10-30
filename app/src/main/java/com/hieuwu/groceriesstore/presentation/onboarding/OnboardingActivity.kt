package com.hieuwu.groceriesstore.presentation.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.MainActivity
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        binding.viewModel = ViewModelProvider(this).get(OnboardingViewModel::class.java)

        binding.viewModel.productSyncStatus.observe(this) {
            if (it!!) {
                //Handle sync product successful
            }
            else {
               //Handle sync product failed
            }
        }

        binding.viewModel.categorySyncStatus.observe(this) {
            if (it!!) {
                //Handle sync category successful
            }
            else {
                //Handle sync category failed
            }
        }
        binding.getStartedButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}