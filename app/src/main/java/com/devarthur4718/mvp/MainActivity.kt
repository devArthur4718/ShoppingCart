package com.devarthur4718.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devarthur4718.mvp.databinding.ActivityMainBinding
import com.devarthur4718.mvp.ui.core.CoreNavDrawerActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btnLogin.setOnClickListener {
            var intent = Intent(this, CoreNavDrawerActivity::class.java)
            finish()
            startActivity(intent)
        }

    }
}
