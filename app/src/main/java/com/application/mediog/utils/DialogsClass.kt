package com.application.mediog.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.application.mediog.MainActivity
import com.application.mediog.R
import com.google.android.material.button.MaterialButton

class DialogsClass(activity: Activity) {

    private val loading = Dialog(activity)

    fun showLoading() {
        loading.setContentView(R.layout.loading_box)
        loading.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // this line used to remove dialog class white background...
        loading.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loading.setCancelable(false)
        loading.show()
    }

    fun stopLoading() {
        loading.dismiss()
    }

    fun showDialog(title: String, activity: Activity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loading.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.error_box)
        val body = dialog.findViewById(R.id.message_text) as TextView
        body.text = title
        dialog.show()
    }

    fun showSucessDialog(title: String, activity: Activity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loading.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.success_dialog)
        val body = dialog.findViewById(R.id.message_text) as TextView
        body.text = title
        dialog.show()
    }


}