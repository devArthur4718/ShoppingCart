package com.devarthur4718.mvp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.BuildConfig
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityRegisterSellerBinding
import com.devarthur4718.mvp.extension.*
import com.devarthur4718.mvp.mock.MockedData
import com.devarthur4718.mvp.repository.database.entity.Business

class RegisterSeller : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterSellerBinding
    private lateinit var viewmodel : RegisterSellerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_seller)
        viewmodel = ViewModelProvider(this).get(RegisterSellerViewModel::class.java)
        initAction()
        initData()

    }

    private fun initData() {
        if(BuildConfig.DEBUG && MockedData.MOCK_APP_DATA){
            binding.inputNewEmail.setText(MockedData.EMAIL)
            binding.inputNewPassword.setText(MockedData.PW)
            binding.inputConfirmPasssword.setText(MockedData.CONFIRM_PW)
        }
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
        var bussiness = Business()
            .apply {
            email = binding.inputNewEmail.editText?.text.toString()
        }

        binding.inputNewEmail.editText?.clearError()
        binding.inputNewPassword.editText?.clearError()

        if(!binding?.inputNewEmail?.editText?.isEmailValid()!!){
            binding.inputNewEmail.editText?.setError(getString(R.string.invalid_email))
            return
        } else if(binding?.inputNewEmail?.editText?.isNullOrEmpty()!!){
            binding.inputNewEmail.editText?.setError(getString(R.string.blank_email))
            return
        } else if (!binding.inputNewPassword.editText?.isPasswordValid()!!) {
            binding.inputNewPassword.editText?.setError(getString(R.string.password_min))
            return
        } else if (binding.inputNewPassword?.editText?.isNullOrEmpty()!!) {
            binding.inputNewPassword?.editText?.setError(getString(R.string.blank_pw))
            return
        }else if (!binding.inputConfirmPasssword.editText?.isPasswordValid()!!) {
            binding.inputConfirmPasssword.editText?.setError(getString(R.string.password_min))
            return
        } else if (binding.inputConfirmPasssword?.editText?.isNullOrEmpty()!!) {
            binding.inputConfirmPasssword?.editText?.setError(getString(R.string.blank_pw))
            return
        }else if(!binding.inputNewPassword.editText!!.text.toString().equals(binding.inputConfirmPasssword.editText!!.text.toString())){
            binding.inputConfirmPasssword.editText?.setError(getString(R.string.pw_not_match))
            return
        }

        viewmodel.saveBusinessData(bussiness)
        val intent = Intent(this, BusinessContactActivity::class.java)
        startActivity(intent)
    }



}
