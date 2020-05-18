package com.devarthur4718.mvp.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        initViews()
        setObservables()
    }

    private fun setObservables() {

    }

    private fun initViews() {
        binding.ivCloseRegistration.setOnClickListener { finish() }

    }
}
