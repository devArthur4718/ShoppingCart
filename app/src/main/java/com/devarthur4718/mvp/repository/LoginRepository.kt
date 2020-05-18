package com.devarthur4718.mvp.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginRepository {

    var auth = FirebaseAuth.getInstance()

    fun logInWithEmailAndPassword(email : String = "email", password : String = "password"): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email,password)
    }
}