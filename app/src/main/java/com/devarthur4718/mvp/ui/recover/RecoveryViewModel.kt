package com.devarthur4718.mvp.ui.recover

import androidx.lifecycle.ViewModel
import com.devarthur4718.mvp.repository.LoginRepository

class RecoveryViewModel : ViewModel() {

    val authRepository = LoginRepository()

    fun recoveryPassword(email : String) : Boolean?{
        return authRepository.recoverPassword(email)
    }


}