package com.devarthur4718.mvp.ui.loading

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.devarthur4718.mvp.R

class LoadingDialog(context : Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.progress)
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun show() {
        if (!this.isShowing) {
            super.show()
        }
    }

    override fun dismiss() {
        if (this.isShowing) {
            super.dismiss()
        }
    }

    override fun cancel() {
        if (this.isShowing) {
            super.cancel()
        }
    }

}