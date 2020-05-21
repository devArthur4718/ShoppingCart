package com.devarthur4718.mvp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityRegisterSellerBinding
import com.devarthur4718.mvp.repository.business.Business

class RegisterSeller : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterSellerBinding
    private lateinit var viewmodel : RegisterSellerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_seller)
        viewmodel = ViewModelProvider(this).get(RegisterSellerViewModel::class.java)
        initAction()
    }

    private fun initAction() {
        binding.buttonNext.setOnClickListener {
            saveFormData()
        }

        binding.imageView3.setOnClickListener {
            finish()
        }

    }

    private fun saveFormData() {
        var bussiness = Business().apply {
            email = binding.inputNewEmail.editText?.text.toString()
        }
        viewmodel.saveBusinessData(bussiness)
        val intent = Intent(this, BusinessContactActivity::class.java)
        startActivity(intent)
    }


}
