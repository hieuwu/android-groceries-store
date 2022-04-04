package com.hieuwu.groceriesstore

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater

class LoadingDialog(context: Context) : Dialog(context) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.loading_layout, null)
        setContentView(view)
        setCancelable(false)
    }
}
