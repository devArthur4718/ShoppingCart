package com.devarthur4718.mvp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.LoginActivityBinding
import com.devarthur4718.mvp.extension.checkInternetAndCall
import com.devarthur4718.mvp.extension.clearError
import com.devarthur4718.mvp.ui.core.CoreActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : LoginActivityBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.login_activity
        )
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        initViews()
        setObservables()

    }

    private fun setObservables() {
        viewModel.userUID.observe(this, Observer { userdata -> onUserLoginSuccess(userdata) })

    }

    private fun onUserLoginSuccess(userdata: String?) {
        userdata?.let {
            navigateToCoreActivity()
        }
    }

    private fun navigateToCoreActivity() {
        startActivity(CoreActivity.getIntent(this))
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            performEmailPasswordLogin(
                binding.inputUsername.editText?.text.toString(),
                binding.inputPassword.editText?.text.toString()
                )
        }
    }

    private fun performEmailPasswordLogin(email : String, password : String) {
        binding.inputUsername.editText?.clearError()
        binding.inputPassword.editText?.clearError()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
//            viewmodel.showLoading()
            checkInternetAndCall {
                viewModel.signInWithEmail(email, password)
            }

        } else {
            if (binding.inputUsername.editText?.text.isNullOrEmpty()) binding.inputUsername.editText?.setError("")
            if (binding.inputPassword.editText?.text.isNullOrEmpty()) binding.inputPassword.editText?.setError("")
        }

    }
}
