package com.hieuwu.groceriesstore.presentation.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                AccountScreen(
                    onSignInClick = ::navigateToAuthentication,
                    onProfileSettingsClick = ::navigateToProfileSettings,
                    onNotificationSettingsClick = ::navigateToNotificationsSettings,
                    onOrderHistorySettingsClick = ::navigateToOrderHistory,
                    onMealPlanningClick = ::navigateToMealPlanning,
                )
            }
        }
    }

    private fun navigateToAuthentication() {
        val intent = Intent(context, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToProfileSettings() {
        findNavController().navigate(R.id.action_accountFragment_to_updateProfileFragment)
    }

    private fun navigateToNotificationsSettings() {
        findNavController().navigate(R.id.action_accountFragment_to_notificationSettingsFragment)
    }

    private fun navigateToOrderHistory() {
        findNavController().navigate(R.id.action_accountFragment_to_orderHistoryFragment)
    }

    private fun navigateToMealPlanning() {
        findNavController().navigate(R.id.action_accountFragment_to_overviewFragment)
    }
}
