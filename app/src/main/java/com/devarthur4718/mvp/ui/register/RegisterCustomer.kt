package com.devarthur4718.mvp.ui.register


import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityRegisterBinding
import com.devarthur4718.mvp.extension.*
import com.devarthur4718.mvp.ui.base.BaseActivity

class RegisterCustomer : BaseActivity() {

    //TODO : Remove Loose Coupling
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var viewmodel : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewmodel = ViewModelProvider(this)[RegisterViewModel::class.java]
        setObservables()
        initUI()
    }

    private fun initUI() {
        binding.ivCloseRegistration.setOnClickListener { finish() }

        binding.btnRegister.setOnClickListener {

            binding.inputName.editText?.clearError()
            binding.inputNewEmail.editText?.clearError()
            binding.inputNewPassword.editText?.clearError()

            //TODO : move validation to the view model
            if(binding.inputName.editText!!.isNullOrEmpty()){
                binding.inputName.editText?.setError(getString(R.string.blank_name))
                return@setOnClickListener
            }
            else if(!binding?.inputNewEmail?.editText?.isEmailValid()!!){
                binding.inputNewEmail.editText?.setError(getString(R.string.invalid_email))
                return@setOnClickListener
            } else if(binding?.inputNewEmail?.editText?.isNullOrEmpty()!!){
                binding.inputNewEmail.editText?.setError(getString(R.string.blank_email))
                return@setOnClickListener
            }else if (!binding.inputNewPassword.editText?.isPasswordValid()!!) {
                binding.inputNewPassword.editText?.setError(getString(R.string.password_min))
                return@setOnClickListener
            } else if (binding.inputNewPassword?.editText?.isNullOrEmpty()!!) {
                binding.inputNewPassword?.editText?.setError(getString(R.string.blank_pw))
                return@setOnClickListener
            }


            checkInternetAndCall {
                viewmodel.registerUser(
                    binding.inputName.editText?.text.toString(),
                    binding.inputNewEmail.editText?.text.toString(),
                    binding.inputNewPassword.editText?.text.toString()
                )
            }

        }
    }

    private fun setObservables() {

        viewmodel.onRegisterSuccess.observe(this, Observer { onRegistrationCallback(it) })
        viewmodel.loadingProgress.observe(this, Observer { onLoadingState(it) })

    }

    private fun onLoadingState(it: Boolean?) {
        it?.let {
            if(it){
                onStartLoading()
            }
            else{
                onStopLoading()
            }
        }
    }

    private fun onRegistrationCallback(status: Boolean?) {
        status?.let { status ->
            if(status){
                Toast.makeText(this, getString(R.string.registration_success), Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, getString(R.string.registration_failed), Toast.LENGTH_SHORT).show()
            }
        }

    }


}
