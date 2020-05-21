package com.devarthur4718.mvp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityBusinessContactBinding
import com.devarthur4718.mvp.extension.clearError
import com.devarthur4718.mvp.extension.getText
import com.devarthur4718.mvp.repository.business.Business
import com.devarthur4718.mvp.ui.location.MapsActivity

class BusinessContactActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBusinessContactBinding
    private lateinit var viewmodel : RegisterSellerViewModel

    companion object{
        const val RQ_MAPS = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_business_contact)
        viewmodel = ViewModelProvider(this).get(RegisterSellerViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        binding.buttonNext.setOnClickListener {
            var data = Business().apply {
                contactname = binding.inputContactName.getText()
                nickname = binding.inputNickName.getText()
            }

            binding.inputContactName.clearError()
            binding.inputNickName.clearError()
            binding.inputBusinessName.clearError()
            binding.inputBussinessAddress.clearError()

            if(binding.inputContactName.editText?.text.isNullOrEmpty()){
                binding.inputContactName.editText?.setError(getString(R.string.blank_name))
                return@setOnClickListener
            } else if(binding.inputNickName.editText?.text.isNullOrEmpty()){
                binding.inputNickName.editText?.setError(getString(R.string.blank_name))
                return@setOnClickListener
            } else if(binding.inputBusinessName.editText?.text.isNullOrEmpty()){
                binding.inputBusinessName.editText?.setError(getString(R.string.blank_name))
                return@setOnClickListener
            } else if(binding.inputBussinessAddress.editText?.text.isNullOrEmpty()){
                binding.inputBussinessAddress.editText?.setError(getString(R.string.blank_name))
                return@setOnClickListener
            }

            viewmodel.updateBussinessData(data)

            val intent = Intent(this,PaymentMethod::class.java)
            startActivity(intent)
        }

        binding.imageView3.setOnClickListener {
            finish()
        }

        binding.SearchInMaps.setOnClickListener {
            var intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent,RQ_MAPS)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
