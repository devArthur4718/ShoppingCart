package com.devarthur4718.mvp.repository.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {


    val db = FirebaseFirestore.getInstance()

    companion object{
        const val DOCUMENT_USERS = "users"
    }

    //region User
    fun insertUser(): CollectionReference {
        // Add a new document with a generated ID
        return db.collection(DOCUMENT_USERS)
    }
}