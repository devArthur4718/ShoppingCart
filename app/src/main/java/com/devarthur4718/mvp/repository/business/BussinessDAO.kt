package com.devarthur4718.mvp.repository.business

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BussinessDAO{

    @Query("SELECT * from business_table where id = $CURRENT_BUSSINESS_ID")
    fun getBussinessContact() : LiveData<List<Business>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(business : Business)


}