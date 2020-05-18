package com.devarthur4718.mvp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.LoginActivityBinding
import com.devarthur4718.mvp.ui.core.CoreNav


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.login_activity
        )


        binding.btnLogin.setOnClickListener {
            var intent = Intent(this, CoreNav::class.java)
            finish()
            startActivity(intent)
        }

    }
}
