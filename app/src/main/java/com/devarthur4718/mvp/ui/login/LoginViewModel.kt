package com.devarthur4718.mvp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devarthur4718.mvp.repository.LoginRepository

class LoginViewModel  : ViewModel(){

    //TODO : Remove Loose Coupling
    var authRepository = LoginRepository()

    private val _userUID = MutableLiveData<String>()
    val userUID: LiveData<String> get() = _userUID

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean> get() = _loadingProgress

    private val _loginError = MutableLiveData<String>()
    val loginError : LiveData<String> get() = _loginError


    fun signInWithEmail(email: String, password: String) {
        authRepository.logInWithEmailAndPassword(email, password)
            .addOnCompleteListener {result ->
                if(result.isSuccessful){
                    result.result?.user?.uid?.let {UID ->
                        hideLoading()
                        sendUserUID(UID)
                    }
                }else{
                    hideLoading()
                    loginError("Usuário e/ou senha incorretos")
                }
            }
            .addOnFailureListener {
                hideLoading()
            }
    }

    fun sendUserUID(UID : String){
        _userUID.value = UID
    }

    fun showLoading(){
        _loadingProgress.value = true
    }

    fun hideLoading(){
        _loadingProgress.value = false
    }


    private fun loginError(msg :String) {
        _loginError.value = msg
    }

}