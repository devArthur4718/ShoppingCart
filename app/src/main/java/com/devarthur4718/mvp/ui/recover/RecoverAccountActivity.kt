package com.devarthur4718.mvp.ui.recover

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ActivityRecoverAccountBinding
import com.devarthur4718.mvp.ui.base.BaseActivity

class RecoverAccountActivity : BaseActivity() {

    private lateinit var binding : ActivityRecoverAccountBinding
    private lateinit var viewmodel: RecoveryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_account)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recover_account)
        viewmodel = ViewModelProvider(this)[RecoveryViewModel::class.java]
        binding.btnSendRecoveryDetails.setOnClickListener {
            recoverUserPassword()
        }

    }

    private fun recoverUserPassword() {
        if (!binding.inputRecoveryEmail.editText?.text.isNullOrEmpty()) {
            var status = viewmodel.recoveryPassword(binding.inputRecoveryEmail.editText?.text.toString())
            status?.let {
                if(it){
                    Toast.makeText(this,getString(R.string.recovery_detail_sent), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,getString(R.string.recovery_detail_sent), Toast.LENGTH_SHORT).show()
                }
            }

            finish()
        }
    }
}
