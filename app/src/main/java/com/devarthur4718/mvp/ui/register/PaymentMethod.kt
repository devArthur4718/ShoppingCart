package com.devarthur4718.mvp.ui.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import com.devarthur4718.mvp.BuildConfig
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityPaymentMethodBinding
import com.devarthur4718.mvp.mock.MockedData
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
        binding.imageView3.setOnClickListener {
            finish()
        }

        if(BuildConfig.DEBUG && MockedData.MOCK_APP_DATA){
            binding.edtPhoneYape.setText(MockedData.PHONE_YAPE)
        }


    }

    fun onItemChecked(view: View) {
        if(view is CheckBox){
            val checked = view.isChecked
            when(view.id){
                R.id.checkYape -> {
                    binding.edtPhoneYape.isEnabled = checked
                }
                R.id.checkBox4 ->{
                    binding.edtTunki.isEnabled = checked
                }
                R.id.checkBox5 -> {
                    binding.edtPhonePlin.isEnabled = checked
                }
            }
        }
    }
}
