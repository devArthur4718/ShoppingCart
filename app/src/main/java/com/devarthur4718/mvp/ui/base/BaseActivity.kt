package com.devarthur4718.mvp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.ui.core.CoreViewModel

open class BaseActivity  : AppCompatActivity(){
    lateinit var mainViewModel: CoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[CoreViewModel::class.java]
    }

}