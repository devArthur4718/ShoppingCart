package com.devarthur4718.mvp.repository.business

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_CUSTOMER_ID = 1

@Entity(tableName = "customer_table")
data class Customer (
    var email: String? = null,
    var contactname : String? = null,
    var nickname : String? = null,
    var userPhoto : String? = null
){

    @PrimaryKey(autoGenerate = false)
    var id : Int = CURRENT_CUSTOMER_ID
}