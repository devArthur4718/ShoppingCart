package com.devarthur4718.mvp.repository.business

import androidx.lifecycle.LiveData

class BusinessRepository(private val businessDao : BussinessDAO) {


    val currentBusiness : LiveData<List<Business>> = businessDao.getBussinessContact()

    suspend fun insert(business: Business){
        businessDao.upsert(business)
    }
}