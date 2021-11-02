package com.hieuwu.groceriesstore.presentation.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hieuwu.groceriesstore.MainActivity
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.ActivityOnboardingBinding
import com.hieuwu.groceriesstore.di.ProductRepo
import com.hieuwu.groceriesstore.domain.repository.CategoryRepository
import com.hieuwu.groceriesstore.domain.repository.ProductRepository
import com.hieuwu.groceriesstore.presentation.shop.ShopFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingBinding

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @ProductRepo
    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPrefs =
            getSharedPreferences(getString(R.string.sync_status_pref_name), Context.MODE_PRIVATE)

        val productSynced = sharedPrefs.getBoolean(getString(R.string.product_sync_success), false)
        val categorySynced =
            sharedPrefs.getBoolean(getString(R.string.category_sync_success), false)

        if (productSynced && categorySynced) {
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        val viewModelFactory = OnboardingViewModelFactory(productRepository, categoryRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.productSyncStatus.observe(this, {
            if (it!!) {
                with(sharedPrefs.edit()) {
                    putBoolean(getString(R.string.product_sync_success), true)
                    apply()
                }

            }
        })


        viewModel.categorySyncStatus.observe(this) {
            if (it) {
                //Handle sync category successful
                with(sharedPrefs.edit()) {
                    putBoolean(getString(R.string.category_sync_success), true)
                    apply()
                }
            } else {
                //Handle sync category failed
            }
        }
        binding.getStartedButton.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            viewModel.updateSyncStatus(true)

        }
    }
}