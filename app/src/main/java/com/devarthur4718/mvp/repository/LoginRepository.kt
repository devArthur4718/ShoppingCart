package com.devarthur4718.mvp.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginRepository {

    var auth = FirebaseAuth.getInstance()

    fun logInWithEmailAndPassword(email : String = "email", password : String = "password"): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email,password)
    }

    fun createUser( email : String,  password : String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("createUser", "Successs")


            }.addOnFailureListener {
                Log.d("createUser", "Fail: $it")
            }
    }

    fun getCurrentUserUID() : String = auth.uid ?: ""

    var recoverStatus : Boolean? = null
    fun recoverPassword(email : String): Boolean? {

        auth.sendPasswordResetEmail(email).addOnCompleteListener {task ->
            if(task.isSuccessful){
                recoverStatus = true
                return@addOnCompleteListener
            }else{
                recoverStatus = false
                return@addOnCompleteListener
            }

        }.addOnFailureListener {
            recoverStatus = false
            return@addOnFailureListener
        }

        return recoverStatus
    }

}