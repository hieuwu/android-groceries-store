package com.hieuwu.groceriesstore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentMainBinding
import com.hieuwu.groceriesstore.databinding.FragmentShopBinding
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentShopBinding>(
            inflater, R.layout.fragment_shop, container, false
        )

        var navController = NavHostFragment.findNavController(this)
        var bottomNavView = binding.bottomNavView
        bottomNavView.setupWithNavController(navController)
        return binding.root
    }


}