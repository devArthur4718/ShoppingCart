package com.devarthur4718.mvp.repository.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FirestoreRepository {


    val db = FirebaseFirestore.getInstance()

    companion object{
        const val DOCUMENT_USERS = "users"
        const val PRODUCTS_CATEGORY = "products-category"
    }

    //region User
    fun insertUser(): CollectionReference {
        // Add a new document with a generated ID
        return db.collection(DOCUMENT_USERS)
    }

    fun fetchCategories(): Query {
        return db.collection(PRODUCTS_CATEGORY).orderBy("category", Query.Direction.DESCENDING)
    }
}