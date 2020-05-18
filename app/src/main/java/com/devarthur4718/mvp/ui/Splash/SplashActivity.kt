package com.devarthur4718.mvp.ui.Splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devarthur4718.mvp.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long =  1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews()
    }

    private fun initViews() {

    }
}
