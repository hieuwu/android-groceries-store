package com.hieuwu.groceriesstore.presentation.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hieuwu.groceriesstore.MainActivity
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.ActivityOnboardingBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.domain.repository.RecipeRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var recipeRepository: RecipeRepository

    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        val sharedPrefs =
            getSharedPreferences(getString(R.string.sync_status_pref_name), Context.MODE_PRIVATE)

        val isSyncedSuccessful = sharedPrefs.getBoolean(getString(R.string.sync_success), false)

        if (isSyncedSuccessful) {
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        val viewModelFactory = OnboardingViewModelFactory(productRepository, categoryRepository, recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.isSyncedSuccessful.observe(this, {
            if (it!!) {
                with(sharedPrefs.edit()) {
                    putBoolean(getString(R.string.sync_success), true)
                    apply()
                }

            }
        })


        binding.getStartedButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}