package com.devarthur4718.mvp.ui.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devarthur4718.mvp.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    //TODO Add depency injection to centralize instances in a single file..
    private val authRepository = LoginRepository()

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean> get() = _loadingProgress

    private val _onRegisterSuccess = MutableLiveData<Boolean>()
    val onRegisterSuccess: LiveData<Boolean> get() = _onRegisterSuccess


    fun performRegisterWithEmail(username : String , email : String, pw : String){
        _loadingProgress.value = true
        authRepository.createUser(email, pw)
            .addOnCompleteListener {task ->
                _loadingProgress.value = false
                if(task.isSuccessful){
                    _onRegisterSuccess.value = true
                }else{
                    try {
                        throw task.exception!!
                    }catch (e : FirebaseAuthUserCollisionException){
                        Log.e( "Auth", task.exception.toString())
//                        _showRegisterError.value = context.getString(R.string.email_in_use_error)
                        _onRegisterSuccess.value = false
                    }
                }
            }.addOnFailureListener {
                Log.e( "Auth", it.toString())
                _loadingProgress.value = false
            }
    }


}