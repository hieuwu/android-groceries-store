package com.hieuwu.groceriesstore.presentation.notificationsettings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentNotificationSettingsBinding
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationSettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class NotificationSettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentNotificationSettingsBinding
    private lateinit var viewModel: NotificationSettingsViewModel


    @Inject
    lateinit var userSettingsUseCase: UserSettingsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentNotificationSettingsBinding>(
            inflater, R.layout.fragment_notification_settings, container, false
        )

        val viewModelFactory = NotificationSettingsViewModelFactory(userSettingsUseCase)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NotificationSettingsViewModel::class.java)
        binding.viewModel = viewModel
        setEventListeners()
        return binding.root
        // Inflate the layout for this fragment
    }

    private fun setEventListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> {
                    // Update user data to backend
                    viewModel.updateNotificationSettings()
                    true
                }
                else -> false
            }
        }
    }
}