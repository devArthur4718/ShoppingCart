package com.devarthur4718.mvp.repository.business

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_table")
data class BusinessData(
    @PrimaryKey
    val contactname : String,
    val nickname : String,
    val business_name : String,
    val business_address : String,
    val yapePhone : String

)