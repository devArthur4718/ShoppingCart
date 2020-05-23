package com.devarthur4718.mvp.ui.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devarthur4718.mvp.repository.business.Customer
import com.devarthur4718.mvp.repository.firestore.FirestoreRepository
import com.devarthur4718.mvp.repository.login.LoginRepository
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    //TODO Add depency injection to centralize instances in a single file..
    private val authRepository = LoginRepository()
    val db = FirestoreRepository()

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean> get() = _loadingProgress

    private val _onRegisterSuccess = MutableLiveData<Boolean>()
    val onRegisterSuccess: LiveData<Boolean> get() = _onRegisterSuccess


    fun registerUser(username : String, email : String, pw : String){
        _loadingProgress.value = true

        var customer = Customer().apply {
            this.email = email
            this.contactname = username
            this.nickname = nickname
            this.userPhoto = userPhoto
        }

        authRepository.createUser(email, pw)
            .addOnCompleteListener {task ->
                _loadingProgress.value = false
                if(task.isSuccessful){
                     saveUserData(customer, task.result?.user?.uid)

                }else{
                    try {
                        throw task.exception!!
                    }catch (e : FirebaseAuthUserCollisionException){
                        Log.e( "Auth", task.exception.toString())
                        _onRegisterSuccess.value = false
                    }
                }
            }.addOnFailureListener {
                Log.e( "Auth", it.toString())
                _loadingProgress.value = false
            }
    }

    private fun saveUserData(customer: Customer, uid: String?) {

        db.insertUser()
            .document(uid!!)
            .set(customer)
            .addOnSuccessListener {
                _loadingProgress.value = false
                _onRegisterSuccess.value = true

            }
            .addOnFailureListener {
                _loadingProgress.value = false
                _onRegisterSuccess.value = false

            }
    }


}