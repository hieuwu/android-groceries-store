package com.hieuwu.groceriesstore

import android.app.Activity
import androidx.appcompat.app.AlertDialog

class LoadingDialog {
    private lateinit var myActivity: Activity
    private lateinit var dialog: AlertDialog

    constructor(activity: Activity) {
        myActivity = activity
    }

    fun showLoadingDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(myActivity)

        val inflater = myActivity.layoutInflater
        val view = inflater.inflate(R.layout.loading_layout, null)

        builder.setView(view)
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissLoadingDialog() {
        dialog.dismiss()
    }


}