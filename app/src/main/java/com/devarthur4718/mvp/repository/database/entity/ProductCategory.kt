package com.devarthur4718.mvp.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class ProductCategory(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var category : String? = null,
    var imgUrl : String = ""

)