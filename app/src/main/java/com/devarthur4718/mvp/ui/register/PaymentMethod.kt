package com.devarthur4718.mvp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityPaymentMethodBinding
import com.devarthur4718.mvp.ui.login.LoginActivity


class PaymentMethod : AppCompatActivity() {

    private lateinit var binding : ActivityPaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_method)
        initViews()
    }

    private fun initViews() {
        binding.btnFinish.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            }
            startActivity(intent)
            finish()
        }
    }
}
