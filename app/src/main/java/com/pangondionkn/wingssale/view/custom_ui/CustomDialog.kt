package com.pangondionkn.wingssale.view.custom_ui

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.pangondionkn.wingssale.R
import com.pangondionkn.wingssale.databinding.PopupWarningBinding

interface PopUpDialogListener{
    fun onClickPopUpListener()
}

fun Activity.showPopupDialog(
    textDesc: String,
    listener: PopUpDialogListener?= null
){
    val dialog = Dialog(this)
    val binding = PopupWarningBinding.bind(layoutInflater.inflate(R.layout.popup_warning, null))

    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(binding.root)
    dialog.setCancelable(listener == null)
    binding.apply {
        tvWarning.text = textDesc
        btnWarning.setOnClickListener {
            listener?.onClickPopUpListener()
            dialog.dismiss()
        }
        if(!isFinishing) dialog.show()
    }
}
