package com.devarthur4718.mvp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.ui.core.CoreViewModel
import com.devarthur4718.mvp.ui.dialog.LoadingDialog

open class BaseActivity  : AppCompatActivity(){
    lateinit var mainViewModel: CoreViewModel
    private val loadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[CoreViewModel::class.java]

    }


    open fun onStartLoading() {
        loadingDialog.show()
    }

    open fun onStopLoading() {
        loadingDialog.dismiss()
    }

}