package com.devarthur4718.mvp.repository.business

import androidx.lifecycle.LiveData
import com.devarthur4718.mvp.repository.database.entity.Business

class BusinessRepository(private val businessDao : BussinessDAO) {


    val currentBusiness : LiveData<List<Business>> = businessDao.getBussinessContact()

    suspend fun insert(business: Business){
        businessDao.upsert(business)
    }
}