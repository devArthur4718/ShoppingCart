package com.devarthur4718.mvp.repository.business

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_BUSSINESS_ID = 0

@Entity(tableName = "business_table")
data class BusinessData(
    val contactname : String,
    val nickname : String,
    val business_name : String,
    val business_address : String,
    val yapePhone : String
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = CURRENT_BUSSINESS_ID

}