package com.devarthur4718.mvp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityRegisterSellerBinding

class RegisterSeller : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterSellerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_seller)
        initAction()
    }

    private fun initAction() {
        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, BusinessContactActivity::class.java)
            startActivity(intent)
        }

    }
}
