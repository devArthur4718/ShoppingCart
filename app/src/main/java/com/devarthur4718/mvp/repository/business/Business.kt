package com.devarthur4718.mvp.repository.business

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_BUSSINESS_ID = 0

@Entity(tableName = "business_table")
data class Business(
    var email: String? = null,
    var contactname : String? = null,
    var nickname : String? = null,
    var business_name : String? = null,
    var business_address : String? = null,
    var yapePhone : String? = null
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = CURRENT_BUSSINESS_ID

}