package com.devarthur4718.mvp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.devarthur4718.mvp.BuildConfig
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.LoginActivityBinding
import com.devarthur4718.mvp.extension.checkInternetAndCall
import com.devarthur4718.mvp.extension.clearError
import com.devarthur4718.mvp.ui.base.BaseActivity
import com.devarthur4718.mvp.ui.core.CoreActivity
import com.devarthur4718.mvp.ui.recover.RecoverAccountActivity
import com.devarthur4718.mvp.ui.register.RegisterCustomer
import com.devarthur4718.mvp.ui.register.RegisterSeller


class LoginActivity : BaseActivity() {

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

        if(BuildConfig.DEBUG){
            binding.inputUsername.editText?.setText("android@gmail.com")
            binding.inputPassword.editText?.setText("12345678")
        }

    }

    private fun setObservables() {
        viewModel.userUID.observe(this, Observer { userdata -> onUserLoginSuccess(userdata) })
        viewModel.loadingProgress.observe(this, Observer {
            if (it)
                onStartLoading()
            else
                onStopLoading()
        })


    }

    private fun onUserLoginSuccess(userdata: String?) {
        userdata?.let {
            navigateToCoreActivity()
        }
    }

    private fun navigateToCoreActivity() {
        var intent = Intent(this, CoreActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            performEmailPasswordLogin(
                binding.inputUsername.editText?.text.toString(),
                binding.inputPassword.editText?.text.toString()
                )
        }

        binding.tvCreateAccount.setOnClickListener {
            navigateToCreateAccount()
        }

        binding.tvRecPw.setOnClickListener {
            navigateToRecoverAccount()
        }
    }

    private fun navigateToRecoverAccount() {
        val intent = Intent(this, RecoverAccountActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToCreateAccount() {
        MaterialDialog(this).show {
            title(text = getString(R.string.title_create))
            message(text = getString(R.string.hint_create))
            positiveButton(text = getString(R.string.buyer_text))
            negativeButton(text = getString(R.string.seller_text))
            positiveButton {
                //Open Buyer text
                openRegisterActivity()
            }
            negativeButton {
                //Open Seller text.
                openSellerActivity()
            }
        }
    }

    private fun openSellerActivity() {
        val intent = Intent(this, RegisterSeller::class.java)
        startActivity(intent)
    }

    private fun openRegisterActivity() {
        val intent = Intent(this, RegisterCustomer::class.java)
        startActivity(intent)
    }

    private fun performEmailPasswordLogin(email : String, password : String) {
        binding.inputUsername.editText?.clearError()
        binding.inputPassword.editText?.clearError()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            viewModel.showLoading()
            checkInternetAndCall {
                viewModel.signInWithEmail(email, password)
            }

        } else {
            if (binding.inputUsername.editText?.text.isNullOrEmpty()) binding.inputUsername.editText?.setError("")
            if (binding.inputPassword.editText?.text.isNullOrEmpty()) binding.inputPassword.editText?.setError("")
        }

    }
}
