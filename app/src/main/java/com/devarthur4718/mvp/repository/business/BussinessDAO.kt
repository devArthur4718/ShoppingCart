package com.devarthur4718.mvp.repository.business

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devarthur4718.mvp.repository.business.BusinessData

@Dao
interface BussinessDAO{


    @Query("SELECT * from business_table")
    fun getBussinessContact() : List<BusinessData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(business : BusinessData)

    @Query("DELETE FROM business_table")
    suspend fun clearData()


}