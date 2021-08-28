package com.hieuwu.groceriesstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hieuwu.groceriesstore.presentation.fragments.CartFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.appNavHostFragment
        ) as NavHostFragment
        var navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        val fabButton = findViewById<FloatingActionButton>(R.id.fabButton)


        val bottomSheetDialogFragment = CartFragment()
        fabButton.setOnClickListener {
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }

        bottomNavigationView.setupWithNavController(navController)

    }
}