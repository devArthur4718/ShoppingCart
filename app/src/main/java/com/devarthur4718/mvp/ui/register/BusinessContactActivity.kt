package com.devarthur4718.mvp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityBusinessContactBinding
import com.devarthur4718.mvp.repository.business.Business

class BusinessContactActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBusinessContactBinding
    private lateinit var viewmodel : RegisterSellerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_business_contact)
        viewmodel = ViewModelProvider(this).get(RegisterSellerViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        binding.buttonNext.setOnClickListener {
            var data = Business().apply {
                contactname = binding.inputContactName.editText?.text.toString()
                nickname = binding.inputNickName.editText?.text.toString()
                email = null
            }

            //TODO : move validation to a base view model.
            viewmodel.updateBussinessData(data)

            val intent = Intent(this,PaymentMethod::class.java)
            startActivity(intent)
        }

        binding.imageView3.setOnClickListener {
            finish()
        }


    }


}
