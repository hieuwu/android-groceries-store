package com.hieuwu.groceriesstore.presentation.checkout

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentCheckOutSuccessBinding

class CheckOutSuccess : DialogFragment() {
    private lateinit var binding: FragmentCheckOutSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentCheckOutSuccessBinding>(
            inflater,
            R.layout.fragment_check_out_success,
            container,
            false
        )

        binding.confirmButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onStart() {
        dialog?.setCancelable(false)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        super.onStart()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.invoke()
    }

    private var dismissListener: (() -> Unit)? = null
    fun setOnDismissListener(callback: (() -> Unit)?) {
        dismissListener = callback
    }
}
