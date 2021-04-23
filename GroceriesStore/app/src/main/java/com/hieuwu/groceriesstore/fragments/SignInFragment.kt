package com.hieuwu.groceriesstore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.hieuwu.groceriesstore.R

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SignInFragment : Fragment() {

    private lateinit var bindingUtil: DataBindingUtil


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot =
            LayoutInflater.from(context).inflate(R.layout.fragment_signin, container, false)
        var binding: ViewDataBinding? = DataBindingUtil.bind(viewRoot)
        binding.apply {
            binding.signinButton.Text = "123";
        }
        return viewRoot;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

}
